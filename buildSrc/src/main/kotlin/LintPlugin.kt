import com.github.ajalt.mordant.terminal.AnsiLevel
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.task
import java.io.File
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.terminal.TextColors
import javax.xml.parsers.DocumentBuilderFactory

class LintPlugin : Plugin<Project> {

    companion object {
        const val ISSUE_TAG = "issue"
        const val ISSUES_LIMIT = 28
    }

    private val terminal = Terminal(ansiLevel = AnsiLevel.TRUECOLOR)

    override fun apply(project: Project) {

        project.task<Exec>("lintCheck") {
            commandLine("./gradlew", "lintLocal")
            doLast {
                var totalIssues = 0

                getReportFiles(project).forEach { file ->
                    totalIssues += countFileIssues(file)
                }

                if (totalIssues > ISSUES_LIMIT) {
                    throw Exception("Number of issues exceeded, current limit is $ISSUES_LIMIT, you have $totalIssues issues")
                } else {
                    logTotalOfIssues(totalIssues)
                }
            }
        }
    }

    private fun logTotalOfIssues(totalIssues: Int) {
        fun print(color: TextColors) {
            val style = (bold + color)
            terminal.println(style("Total of issues is $totalIssues, check the reports to see how you can help to kill some of this issues\n"))
        }

        when {
            ISSUES_LIMIT - totalIssues < 5 -> print(red)
            ISSUES_LIMIT - totalIssues < 20 -> print(yellow)
            else -> print(green)
        }
    }

    private fun getReportFiles(project: Project): Sequence<File> {
        return File("${project.rootDir}/reports/")
            .walk()
            .filter { it.name.endsWith(".xml") }
    }

    private fun countFileIssues(file: File): Int {
        val xmlDoc = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(file)

        val issues = xmlDoc.getElementsByTagName(ISSUE_TAG).length

        terminal.println(yellow("${file.name.split("-").first()} has $issues issues"))

        return issues
    }

}
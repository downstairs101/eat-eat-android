---


---

<h1 id="step-bar">Step Bar</h1>
<p>With this library you can have a step bar that allows you to manage multiple fragment steps in a simple way.</p>
<p><a href="https://jitpack.io/#roubertedgar/step-bar"><img src="https://jitpack.io/v/roubertedgar/step-bar.svg" alt=""></a>    <a href="https://android-arsenal.com/details/1/6848"><img src="https://img.shields.io/badge/Android%20Arsenal-Simple%20Step%20Bar-green.svg?style=flat" alt="Android Arsenal"></a></p>
<hr>
<p><img src="https://media.giphy.com/media/nbOPo5sSZiEmM8YjTc/giphy.gif" alt="Alt Text"></p>
<h3 id="add-viewpager-and-stepbar-on-layout">Add ViewPager and StepBar on layout</h3>
<p>The step bar is a viewGroup, so, you can put a close tag like<br>
&lt; /StepBar&gt;<br>
and put some other view inside it.</p>
<pre class=" language-xml"><code class="prism  language-xml"><span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>android.support.v4.view.ViewPager</span>
    <span class="token attr-name"><span class="token namespace">android:</span>id</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@+id/viewPager<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_width</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_height</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_above</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@id/stepBar<span class="token punctuation">"</span></span> <span class="token punctuation">/&gt;</span></span>

<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>com.steps.StepBar</span>
    <span class="token attr-name"><span class="token namespace">android:</span>id</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@+id/stepBar<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_width</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_height</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
    <span class="token attr-name"><span class="token namespace">android:</span>layout_alignParentBottom</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>true<span class="token punctuation">"</span></span> <span class="token punctuation">/&gt;</span></span>
</code></pre>
<h3 id="implementation-of-view">Implementation of view</h3>
<p>Here you have to give a view pager to the step bar.  After complete all steps, the step bar will call the  onComplete of your onCompleteListener. It gives you a bundle with all values of your steps. You can see an simple implementation of it below:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin">    <span class="token keyword">override</span> <span class="token keyword">fun</span> <span class="token function">onCreate</span><span class="token punctuation">(</span>savedInstanceState<span class="token operator">:</span> Bundle<span class="token operator">?</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>
          <span class="token keyword">super</span><span class="token punctuation">.</span><span class="token function">onCreate</span><span class="token punctuation">(</span>savedInstanceState<span class="token punctuation">)</span>
          <span class="token function">setContentView</span><span class="token punctuation">(</span>R<span class="token punctuation">.</span>layout<span class="token punctuation">.</span>step_container_activity<span class="token punctuation">)</span>

          <span class="token keyword">val</span> steps <span class="token operator">=</span> listOf<span class="token operator">&lt;</span>Step<span class="token operator">&gt;</span><span class="token punctuation">(</span><span class="token function">FirstStepFragment</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">,</span> <span class="token function">SecondStepFragment</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span>

          viewPager<span class="token punctuation">.</span>adapter <span class="token operator">=</span> <span class="token function">SampleStepAdapter</span><span class="token punctuation">(</span>steps<span class="token punctuation">,</span> supportFragmentManager<span class="token punctuation">)</span>
          stepBar<span class="token punctuation">.</span><span class="token function">setViewPager</span><span class="token punctuation">(</span>viewPager<span class="token punctuation">)</span>
      <span class="token punctuation">}</span>
</code></pre>
<h3 id="getting-results">Getting results</h3>
<p>To get the results after done all steps, you can use:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin">  <span class="token comment">//After compleet the stpes, onComplete will be called</span>
  stepBar<span class="token punctuation">.</span><span class="token function">setOnCompleteListener</span><span class="token punctuation">(</span><span class="token punctuation">{</span>
      <span class="token keyword">val</span> intent <span class="token operator">=</span> <span class="token function">Intent</span><span class="token punctuation">(</span><span class="token keyword">this</span><span class="token punctuation">,</span>ResultActivity<span class="token operator">::</span><span class="token keyword">class</span><span class="token punctuation">.</span>java<span class="token punctuation">)</span>
      intent<span class="token punctuation">.</span><span class="token function">putExtras</span><span class="token punctuation">(</span>it<span class="token punctuation">)</span>
      <span class="token function">startActivity</span><span class="token punctuation">(</span>intent<span class="token punctuation">)</span>

  <span class="token comment">//Where 'it' is a bundle that concat all bundles of 'value' variable, returned by each step</span>
  <span class="token punctuation">}</span><span class="token punctuation">)</span>
</code></pre>
<h3 id="the-fragment">The Fragment</h3>
<p>All the step fragments has to Extends support.v4.app.Fragment and implements Step:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin"><span class="token keyword">class</span> FirstStepFragment <span class="token operator">:</span> <span class="token function">Fragment</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">,</span> Step <span class="token punctuation">{</span>
</code></pre>
<p>with this, you have to provide a implementation to var value:Bundle, like this:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin">  <span class="token keyword">override</span> <span class="token keyword">var</span> value<span class="token operator">:</span> Bundle
  <span class="token keyword">get</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token operator">=</span> <span class="token function">getValues</span><span class="token punctuation">(</span><span class="token punctuation">)</span>
  <span class="token keyword">set</span><span class="token punctuation">(</span>value<span class="token punctuation">)</span> <span class="token punctuation">{</span><span class="token punctuation">}</span>

  <span class="token keyword">private</span> <span class="token keyword">fun</span> <span class="token function">getValues</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token operator">:</span> Bundle <span class="token punctuation">{</span>
      <span class="token keyword">val</span> bundle <span class="token operator">=</span> <span class="token function">Bundle</span><span class="token punctuation">(</span><span class="token punctuation">)</span>
      bundle<span class="token punctuation">.</span><span class="token function">putString</span><span class="token punctuation">(</span><span class="token string">"keyStepExample"</span><span class="token punctuation">,</span> <span class="token string">"some value"</span><span class="token punctuation">)</span>
      <span class="token keyword">return</span> bundle
  <span class="token punctuation">}</span>

<span class="token comment">//this value variable will be concat on done all steps and returned to</span>
<span class="token comment">//onCompleteListener that you set in stepBar.setOnCompleteListener()</span>
</code></pre>
<p>And implements invalidateStep method to, like this:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin">    <span class="token keyword">override</span> <span class="token keyword">fun</span> <span class="token function">invalidateStep</span><span class="token punctuation">(</span>invalidate<span class="token operator">:</span> <span class="token punctuation">(</span>isValid<span class="token operator">:</span> Boolean<span class="token operator">?</span><span class="token punctuation">)</span> <span class="token operator">-&gt;</span> Unit<span class="token punctuation">{</span>
        <span class="token function">invalidate</span><span class="token punctuation">(</span><span class="token boolean">true</span><span class="token punctuation">)</span> <span class="token comment">//this step will be ever valid</span>
     <span class="token punctuation">}</span>
</code></pre>
<p>For example, if you want to validate the step checking if some edit text is empty or not, you could do this:</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin">    <span class="token keyword">override</span> <span class="token keyword">fun</span> <span class="token function">invalidateStep</span><span class="token punctuation">(</span>invalidate<span class="token operator">:</span> <span class="token punctuation">(</span>isValid<span class="token operator">:</span> Boolean<span class="token operator">?</span><span class="token punctuation">)</span> <span class="token operator">-&gt;</span> Unit<span class="token punctuation">)</span> <span class="token punctuation">{</span>
        <span class="token keyword">this</span><span class="token punctuation">.</span>invalidate <span class="token operator">=</span> invalidate

        <span class="token function">validate</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token comment">//This is for validate when step back, it keeps the previous valid step valid</span>
    <span class="token punctuation">}</span>

    <span class="token keyword">private</span> <span class="token keyword">fun</span> <span class="token function">validate</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token operator">=</span> <span class="token function">invalidate</span><span class="token punctuation">(</span>editText<span class="token operator">?</span><span class="token punctuation">.</span>text<span class="token operator">?</span><span class="token punctuation">.</span><span class="token function">let</span> <span class="token punctuation">{</span> <span class="token operator">!</span>it<span class="token punctuation">.</span><span class="token function">isEmpty</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">}</span><span class="token punctuation">)</span>
</code></pre>
<h3 id="stepadapter">StepAdapter</h3>
<p>To work with step bar, your adapter has to extends the StepAdapter and implements getCount and getStep</p>
<pre class=" language-kotlin"><code class="prism  language-kotlin"><span class="token keyword">class</span> SampleStepAdapter <span class="token keyword">constructor</span><span class="token punctuation">(</span><span class="token keyword">private</span> <span class="token keyword">val</span> steps<span class="token operator">:</span> List<span class="token operator">&lt;</span>Step<span class="token operator">&gt;</span><span class="token punctuation">,</span> fm<span class="token operator">:</span>FragmentManager<span class="token punctuation">)</span> <span class="token operator">:</span> <span class="token function">StepAdapter</span><span class="token punctuation">(</span>fm<span class="token punctuation">)</span> <span class="token punctuation">{</span>

    <span class="token keyword">override</span> <span class="token keyword">fun</span> <span class="token function">getCount</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token operator">:</span> Int <span class="token operator">=</span> steps<span class="token punctuation">.</span>size

    <span class="token keyword">override</span> <span class="token keyword">fun</span> <span class="token function">getStep</span><span class="token punctuation">(</span>position<span class="token operator">:</span> Int<span class="token punctuation">)</span><span class="token operator">:</span> Step <span class="token operator">=</span> steps<span class="token punctuation">[</span>position<span class="token punctuation">]</span>
<span class="token punctuation">}</span>
</code></pre>
<h3 id="customize">Customize</h3>
<p>For now you can customize 3 attributes of step bar.</p>
<p>First, import custom attributes</p>
<pre class=" language-xml"><code class="prism  language-xml"> xmlns:step="http://schemas.android.com/apk/res-auto"
</code></pre>
<p><em><strong>buttons_tint</strong></em>: this attribute changes the drawable color of back and next step buttons</p>
<pre class=" language-xml"><code class="prism  language-xml"><span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>com.steps.StepBar</span>
      <span class="token attr-name"><span class="token namespace">android:</span>id</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@+id/stepBar<span class="token punctuation">"</span></span>
      <span class="token attr-name"><span class="token namespace">android:</span>layout_width</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
      <span class="token attr-name"><span class="token namespace">android:</span>layout_height</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
      <span class="token attr-name"><span class="token namespace">android:</span>layout_alignParentBottom</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>true<span class="token punctuation">"</span></span>
      <span class="token attr-name"><span class="token namespace">step:</span>buttons_tint</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@color/colorAccent<span class="token punctuation">"</span></span><span class="token punctuation">/&gt;</span></span>
</code></pre>
<p><em><strong>done_text_tint</strong></em>: Using this, you can customize the color of done button text</p>
<pre class=" language-xml"><code class="prism  language-xml"><span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>com.steps.StepBar</span>
        <span class="token attr-name"><span class="token namespace">android:</span>id</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@+id/stepBar<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_width</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_height</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_alignParentBottom</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>true<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">step:</span>done_text_tint</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@color/colorPrimary<span class="token punctuation">"</span></span><span class="token punctuation">/&gt;</span></span>
</code></pre>
<p>if you don’t customize the button text tint, the tint of done text will be the same of back and next step buttons</p>
<p><em><strong>done_button_text</strong></em> : You can change the text of done button, that comes by default as “DONE”</p>
<pre class=" language-xml"><code class="prism  language-xml"><span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>com.steps.StepBar</span>
        <span class="token attr-name"><span class="token namespace">android:</span>id</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>@+id/stepBar<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_width</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_height</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>wrap_content<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">android:</span>layout_alignParentBottom</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>true<span class="token punctuation">"</span></span>
        <span class="token attr-name"><span class="token namespace">step:</span>done_button_text</span><span class="token attr-value"><span class="token punctuation">=</span><span class="token punctuation">"</span>Some other text<span class="token punctuation">"</span></span> <span class="token punctuation">/&gt;</span></span>
</code></pre>


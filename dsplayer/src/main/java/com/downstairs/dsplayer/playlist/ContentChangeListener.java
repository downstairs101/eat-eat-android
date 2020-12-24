package com.downstairs.dsplayer.playlist;

import com.downstairs.dsplayer.content.Content;

public interface ContentChangeListener {
    void onContentChanged(Content content);
}
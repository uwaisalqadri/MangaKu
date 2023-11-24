package com.uwaisalqadri.mangaku.presentation.browse

sealed class BrowseEvent {
    data object GetMangas: BrowseEvent()
}
package com.uwaisalqadri.mangaku.android.presentation.browse

sealed class BrowseAction {
    data object GetMangas: BrowseAction()
}
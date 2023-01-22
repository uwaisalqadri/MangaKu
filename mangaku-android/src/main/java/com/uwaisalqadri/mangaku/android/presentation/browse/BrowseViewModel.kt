package com.uwaisalqadri.mangaku.android.presentation.browse

//class BrowseViewModel(
//    private val browseUseCase: BrowseUseCase
//): ViewModel() {
//
//    private val _trendingManga = MutableStateFlow<Result<List<Manga>>>(com.uwaisalqadri.mangaku.presentation.Result.default())
//    val trendingManga: StateFlow<Result<List<Manga>>> = _trendingManga.asStateFlow()
//
//    fun getTrendingManga() = viewModelScope.launch {
//        _trendingManga.value = com.uwaisalqadri.mangaku.presentation.Result.loading()
//        collectFlow(_trendingManga) {
//            browseUseCase.getTrendingManga()
//        }
//    }
//}
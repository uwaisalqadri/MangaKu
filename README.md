<h1 align="center"> MovieCatalogue</h1> <br>
<p align="center">
  <a href="https://gitpoint.co/">
    <img alt="MovieCatalogue" title="MovieCatalogue" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fapptractor.ru%2Fwp-content%2Fuploads%2F2020%2F09%2Fkotlin-main.png&f=1&nofb=1" width="200">
  </a>
</p>

## <a name="introduction"></a> 🤖 Introduction

A sample project TMDB client build with Kotlin Multiplatform Mobile based on NBS recruitment test

**Design**

[Muvi-App-(NBS)](https://www.figma.com/file/2xOtQaTpTgxvdB7LRSmSPK/Muvi-App-(NBS))

**Module**

* **`core`**: data and domain layer
* **`movieIos`**: ios presentation layer
* **`movieAndroid`**: android presentation layer
* **`buildSrc`**: `movieAndroid` and `core` dependencies

**Branch**
* **`main`**: main branch
* **`flow-coroutine`**: using flow coroutine
* **`reaktive`**: using reaktive **(in progress)**

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Screenshot](#screenshot)
- [Libraries](#libraries)
- [Domain to Presentation](#domain-to-presentation)
- [Expect and Actual](#expect-actual)
- [Project Structure](#project-structure)

## <a name="features"></a> 🦾 Features

A few things you can do with MovieCatalogue:

* View Popular, Upcoming, And Latest Release Movie
* Easily search for any movie
* See Movie Detail
* Save, Remove, and Search your favorite movie

## <a name="installation"></a> 🚗 Installation

- Follow the [KMM Guide by Jetbrains](https://kotlinlang.org/docs/kmm-overview.html) for getting started building a project with KMM.
- Install Kotlin Multiplatform Mobile plugin and SQLDelight plugin (optional) in Android Studio
- Clone or download the repo
- Rebuild Project
- To run in iOS, Open Xcode and `pod install` inside `movieIos` package to install shared module and ios dependencies

**Development Keys**: The `apiKey` in [`utils/Constants.kt`](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/commonMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/Constants.kt) are generated from [TMDB](https://www.themoviedb.org/), generate your own in [themoviedb.org/settings/api](https://www.themoviedb.org/settings/api).

## <a name="screenshot"></a> 📸 Screenshot

<p align="center">
  <img src = "https://i.ibb.co/K0fPv1s/Screen-Shot-2021-10-04-at-13-56-33.png" width=400>
</p>

## <a name="libraries"></a> 💡 Libraries

`core`:
* [Ktor](https://github.com/ktorio/ktor)
* [SQLDelight](https://github.com/cashapp/sqldelight)
* [KMPNativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Kermit](https://github.com/touchlab/Kermit)

`movieIos`:
* [RxSwift](https://github.com/ReactiveX/RxSwift)
* [SDWebImage](https://github.com/SDWebImage/SDWebImage)
* [SVProgressHUD](https://github.com/SVProgressHUD/SVProgressHUD)
* [Reusable](https://github.com/AliSoftware/Reusable)
* [PinLayout](https://github.com/layoutBox/PinLayout)

`movieAndroid`:
* [Gropie](https://github.com/lisawray/groupie)
* [ViewBindingDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)
* [Glide](https://github.com/bumptech/glide)
* [Koin](https://github.com/InsertKoinIO/koin)
* Some Kotlinx & Jetpack Components

## <a name="domain-to-presentation"></a> 💨 Domain to Presentation
In Android, Because both `core` and `movieAndroid` write in Kotlin, we can simply collect flow :
```
private fun requestPopularMovie() = viewModelScope.launch {
  popularUseCase.execute().collect {			
    loading.value = false
    popularMovie.value = it.slice(0 until 10)		
  }
}

```

But in iOS, we need to deal with swift, here i'm using `createObservable()` from `KMPNativeCoroutines` to collect flow as Observable in `RxSwift` :

```
func requestPopularMovie() {
  createObservable(for: popularUseCase.executeNative(year: Extensions().currentYear, page: 1, sortBy: .popularity))
    .observe(on: MainScheduler.instance)
    .subscribe(onNext: { [weak self] result in
      self?.popularMovies.accept(Array(result[0...9]))
  }).disposed(by: disposeBag)
}


```
## <a name="expect-actual"></a> 🚀 Expect and Actual
in KMM, there is a negative case when there's no support to share code for some feature in both ios and android, and it's expensive to write separately in each module

so the solution is ✨`expect` and `actual`✨, we can write `expect` inside `commonMain` and write "actual" implementation with `actual` inside `androidMain` and `iosMain`
and then each module will use `expect`

example:

[**`commonMain/utils/DateFormatter.kt`**](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/commonMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/DateFormatter.kt)
```
expect fun formatDate(dateString: String, format: String): String
```

[**`androidMain/utils/DateFormatter.kt`**](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/androidMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/DateFormatter.kt)

DateTimeFormatter and SimpleDateFormat
```
actual fun formatDate(dateString: String, format: String): String {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		val dateFormat = DateTimeFormatter.ofPattern(Constants.formatFromApi)
		val currentDate = LocalDate.parse(dateString, dateFormat)
		currentDate.format(DateTimeFormatter.ofPattern(format))
	} else {
		val date = SimpleDateFormat(Constants.formatFromApi).parse(dateString)
		val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
		dateFormatter.format(date ?: Date())
	}

}

```

[**`iosMain/utils/DateFormatter.kt`**](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/iosMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/DateFormatter.kt)

NSDateFormatter

```
actual fun formatDate(dateString: String, format: String): String {

	val dateFormatter = NSDateFormatter().apply {
		dateFormat = Constants.formatFromApi
	}

	val formatter = NSDateFormatter().apply {
		dateFormat = format
		locale = NSLocale(localeIdentifier = "id_ID")
	}

	return formatter.stringFromDate(dateFormatter.dateFromString(dateString) ?: NSDate())
}

```
yes, we can use `Foundation` same as what we use in Xcode

## <a name="project-structure"></a> 🏛 Project Structure
**`core`**:

* `data`
  - `mapper`
    - `entity`
    - `response`
  - `repository`
  - `source`
    - `local`
    - `remote`
* `di`
* `domain`
  - `model`
  - `repository`
  - `usecase`
    - `home`
    - `favorite`
    - `search`
    - `detail` 
* `utils`

**`movieAndroid`**:
 - `ui`
    - `home`
    - `favorite`
    - `search`
    - `detail` 
- `di`
- `utils`

**`movieIos`**: 
 - `Dependency`
 - `App`
 - `Main`
 - `Resources`
 - `ReusableView`
 - `Extensions`
 - `Utils`
 - `Features`
    - `Home`
        - `Navigator`
        - `ViewController`
        - `ViewModel`
        - `Views`
    - `Search`
    - `Detail`
    - `Favorite`

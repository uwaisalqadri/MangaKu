<h1 align="center"> MangaKu</h1> <br>
<p align="center">
  <a href="https://gitpoint.co/">
    <img alt="MovieCatalogue" title="MovieCatalogue" src="https://cdn.dribbble.com/users/5027078/screenshots/12022789/media/3e928c7fa9ac0a4e0c320c81302917ea.png" width="500">
  </a>
</p>

## <a name="introduction"></a> 🤖 Introduction

MangaKu App Powered by Kotlin Multiplatform Mobile, Jetpack Compose, and SwiftUI

**Module**

* **`core`**: data and domain layer
* **`iosApp`**: ios presentation layer
* **`androidApp`**: android presentation layer
* **`buildSrc`**: `androidApp` and `core` dependencies

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

A few things you can do with MangaKu:

* View Popular Manga
* Easily search for any Manga
* See Manga Detail
* Save your favorite manga

## <a name="installation"></a> 🚗 Installation

- Follow the [KMM Guide by Jetbrains](https://kotlinlang.org/docs/kmm-overview.html) for getting started building a project with KMM.
- Install Kotlin Multiplatform Mobile plugin in Android Studio
- Clone or download the repo
- Rebuild Project
- To run in iOS, Open Xcode and `pod install` inside `iosApp` folder to install shared module and ios dependencies

<!-- **Development Keys**: The `apiKey` in [`utils/Constants.kt`](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/commonMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/Constants.kt) are generated from [TMDB](https://www.themoviedb.org/), generate your own in [themoviedb.org/settings/api](https://www.themoviedb.org/settings/api). -->

## <a name="screenshot"></a> 📸 Screenshot

<!-- <p align="center">
  <img src = "https://i.ibb.co/K0fPv1s/Screen-Shot-2021-10-04-at-13-56-33.png" width=400>
</p> -->

## <a name="libraries"></a> 💡 Libraries

`core`:
* [Ktor](https://github.com/ktorio/ktor)
* [Realm-Kotlin](https://github.com/realm/realm-kotlin)
* [KMPNativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Kermit](https://github.com/touchlab/Kermit)

`iosApp`:
* [Combine](https://developer.apple.com/documentation/combine)
* [SDWebImage](https://github.com/SDWebImage/SDWebImage)
* [Lottie-iOS](https://github.com/airbnb/lottie-ios)
* [SwiftUI](https://developer.apple.com/documentation/swiftui)

`androidApp`:
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Accompanist](https://github.com/google/accompanist)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Voyager](https://github.com/adrielcafe/voyager)
* Some Kotlinx & Jetpack Components

## <a name="domain-to-presentation"></a> 💨 Domain to Presentation
In Android, Because both `core` and `androidApp` write in Kotlin, we can simply collect flow :
```
private fun fetchManga() = viewModelScope.launch {
     _uiState.value = UiState(loading = true)
     trendingUseCase().collect { result ->
     if (result.isNotEmpty()) _uiState.value = UiState(listManga = result)    
   }
}

```

But in iOS, we need to deal with swift, here i'm using `createPublisher()` from `KMPNativeCoroutines` to collect flow as Publisher in `Combine` :

```
private func fetchTrendingManga() {
  loading = true
  createPublisher(for: trendingUseCase.invokeNative())
    .receive(on: DispatchQueue.main)
    .sink { completion in
       switch completion {
        case .finished:
          self.loading = false
        case .failure(let error):
          self.errorMessage = error.localizedDescription
       }
   } receiveValue: { value in
     self.trendingManga = value
   }.store(in: &cancellables)
}


```
## <a name="expect-actual"></a> 🚀 Expect and Actual
in KMM, there is a negative case when there's no support to share code for some feature in both ios and android, and it's expensive to write separately in each module

so the solution is ✨`expect` and `actual`✨, we can write `expect` inside `commonMain` and write "actual" implementation with `actual` inside `androidMain` and `iosMain`
and then each module will use `expect`

example:

[**`commonMain/utils/DateFormatter.kt`**](https://github.com/uwaisalqadri/MangaKu/blob/master/core/src/commonMain/kotlin/com/uwaisalqadri/mangaku/utils/DateFormatter.kt)
```
expect fun formatDate(dateString: String, format: String): String
```

[**`androidMain/utils/DateFormatter.kt`**](https://github.com/uwaisalqadri/MangaKu/blob/master/core/src/androidMain/kotlin/com/uwaisalqadri/mangaku/utils/DateFormatter.kt)

SimpleDateFormat

```
actual fun formatDate(dateString: String, format: String): String {
    val date = SimpleDateFormat(Constants.formatFromApi).parse(dateString)
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(date ?: Date())
}

```

[**`iosMain/utils/DateFormatter.kt`**](https://github.com/uwaisalqadri/MangaKu/blob/master/core/src/iosMain/kotlin/com/uwaisalqadri/mangaku/utils/DateFormatter.kt)

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
    	- `entity`
    - `remote`
      - `response`
* `di`
* `domain`
  - `model`
  - `repository`
  - `usecase`
    - `browse`
    - `detail`
    - `mymanga`
    - `search` 
* `utils`

**`androidApp`**:
 - `ui`
    - `composables`
    - `home`
      - `composables`
    - `favorite`
    - `search`
    - `detail` 
- `di`
- `utils`

**`iosApp`**: 
 - `Dependency`
 - `App`
 - `Main`
 - `Resources`
 - `ReusableView`
 - `Extensions`
 - `Utils`
 - `Features`
    - `Browse`
        - `Views`
    - `Search`
    - `Detail`
    - `MyManga`

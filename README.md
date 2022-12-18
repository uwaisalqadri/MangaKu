<h1 align="center"> MangaKu</h1> <br>
<p align="center">
  <a href="https://gitpoint.co/">
    <img alt="Mangaku" title="Mangaku" src="https://cdn.dribbble.com/users/5027078/screenshots/12022789/media/3e928c7fa9ac0a4e0c320c81302917ea.png" width="500">
  </a>
</p>

## <a name="introduction"></a> 🤖 Introduction

MangaKu App Powered by Kotlin Multiplatform Mobile, Jetpack Compose, and SwiftUI

**Module**

* **`shared`**: data and domain layer
* **`mangaku-ios`**: ios presentation layer
* **`mangaku-android`**: android presentation layer
* **`buildSrc`**: `mangaku-android` and `shared` dependencies

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

⚠️ **`This project have no concern about backward compatibility, and only support the very latest or experimental api's for both android and ios `** ⚠️

## <a name="installation"></a> 🚗 Installation

- Follow the [KMM Guide by Jetbrains](https://kotlinlang.org/docs/kmm-overview.html) for getting started building a project with KMM.
- Install Kotlin Multiplatform Mobile plugin in Android Studio
- Clone or download the repo
- Rebuild Project
- To run in iOS, Open Xcode and `pod install` inside `mangaku-ios` folder to install shared module and ios dependencies

<!-- **Development Keys**: The `apiKey` in [`utils/Constants.kt`](https://code.nbs.dev/nbs-mobile/kmm-movie-db/-/blob/main/core/src/commonMain/kotlin/com/uwaisalqadri/moviecatalogue/utils/Constants.kt) are generated from [TMDB](https://www.themoviedb.org/), generate your own in [themoviedb.org/settings/api](https://www.themoviedb.org/settings/api). -->

## <a name="screenshot"></a> 📸 Screenshot

<!-- <p align="center">
  <img src = "https://i.ibb.co/K0fPv1s/Screen-Shot-2021-10-04-at-13-56-33.png" width=400>
</p> -->

## <a name="libraries"></a> 💡 Libraries

`shared`:
* [Ktor](https://github.com/ktorio/ktor)
* [Realm-Kotlin](https://github.com/realm/realm-kotlin)
* [KMPNativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Kermit](https://github.com/touchlab/Kermit)

`mangaku-ios`:
* [Swift's New Concurrency](https://developer.apple.com/news/?id=2o3euotz)
* [SDWebImage](https://github.com/SDWebImage/SDWebImage)
* [SwiftUI](https://developer.apple.com/documentation/swiftui)

`mangaku-android`:
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Accompanist](https://github.com/google/accompanist)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Compose Destinations](https://github.com/raamcosta/compose-destinations)
* Some Kotlinx & Jetpack Components

## <a name="domain-to-presentation"></a> 💨 Domain to Presentation
In Android, Because both `shared` and `mangaku-android` written in Kotlin, we can simply collect flow :
```
private fun getTrendingManga() = viewModelScope.launch {
  _trendingManga.value = Result.loading()
  browseUseCase.getManga()
   .catch { cause: Throwable ->
     _trendingManga.value = Result.failed(cause)
   }
   .collect { result ->
     if (result.isNotEmpty())
     _trendingManga.value = Result.success(result)
   }
 }

```

But in iOS, we have to deal with swift, here i'm using `createPublisher()` from `KMPNativeCoroutines` to collect flow as Publisher in `Combine` :

```
func fetchTrendingManga() {
  trendingManga = .loading
  createPublisher(for: browseUseCase.getTrendingMangaNative())
   .receive(on: DispatchQueue.main)
   .sink { completion in
     switch completion {
       case .finished: ()
       case .failure(let error):
         self.trendingManga = .error(error: error)
       }
    } receiveValue: { value in
        self.trendingManga = .success(data: value)
    }.store(in: &cancellables)
}

```

or even better, you can use `asyncFunction` / `asyncResult` / `asyncStream` function to collect coroutine flow as new swift's concurrency features, ~~checkout branch **feat/experimenting-swift-new concurrency** to see the example~~

**combining two powerful concurrency feature from both native framework, how cool is that !?**

```
func fetchTrendingManga() {
    Task {
      trendingManga = .loading
      do {
        let nativeFlow = try await asyncFunction(for: browseUseCase.getTrendingMangaNative())
        let stream = asyncStream(for: nativeFlow)
        for try await data in stream {
          trendingManga = .success(data: data)
        }
      } catch {
        trendingManga = .error(error: error)
      }
    }
  }

```

learn more: https://github.com/rickclephas/KMP-NativeCoroutines

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

## <a name="buy-me-coffee"></a> ☕️ Buy Me a Coffee
If you like this project please support me by <a href="https://www.buymeacoffee.com/uwaisalqadri" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-blue.png" alt="Buy Me A Coffee" height=32></a> ;-)

## <a name="project-structure"></a> 🏛 Project Structure
**`shared`**:

* `data`
  - `mapper`
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

**`mangaku-android`**:
 - `ui`
    - `composables`
    - `home`
      - `composables`
    - `favorite`
    - `search`
    - `detail` 
- `di`
- `utils`

**`mangaku-ios`**: 
 - `Dependency`
 - `App`
 - `Main`
 - `Resources`
 - `ReusableView`
 - `Extensions`
 - `Utils`
 - `Features`
    - `Browse`
        - `Navigator`
        - `Views`
    - `Search`
    - `Detail`
    - `MyManga`

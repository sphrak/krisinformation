[![travis-ci](https://travis-ci.org/vadret/android.svg?branch=master)](https://travis-ci.org/vadret/android)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

![Krisinformation](https://git.kroon.fi/aresfour/krisinformation/raw/branch/master/assets/logo.png)

# Krisinformation
**Krisinformation** is a Swedish emergency information app powered by Swedish Civil Contingencies Agency. The app gets
data from [Krisinformation](https://www.krisinformation.se/english). That site in turn gets data from several other
Swedish authorities such as [Myndigheten för Samhällsskydd och Beredskap](https://www.msb.se/sv/Produkter--tjanster/Oppna-data/) 
and [SMHI](https://www.smhi.se/) among others. API documentation in Swedish is available [here](https://www.krisinformation.se/om-krisinformation/oppen-data) -- the
data itself is licensed under [Creative commons Erkännande 4.0 SE](#).

## Krisinformation API

* [API Endpoints](https://api.krisinformation.se/)
* [Links data](https://api.krisinformation.se/v1/links?format=json)
* [CapMessage data](https://api.krisinformation.se/v1/capmessage?format=json)
* [Feed data](https://api.krisinformation.se/v1/feed?format=json)
* [Theme data](https://api.krisinformation.se/v1/themes?format=json)

## Download

[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png"
      alt="Download from Google Play"
      height="80">](https://play.google.com/store/apps/details?id=fi.kroon.krisinformation)
[<img src="https://f-droid.org/badge/get-it-on.png"
      alt="Get it on F-Droid"
      height="80">](https://f-droid.org/packages/fi.kroon.krisinformation/)

## Architecture
This project tries to obey the [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) approach to application design.

## Android Architecture Components

* [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/)
* [Android KTX](https://developer.android.com/kotlin/ktx)

### Libraries

* [RxJava2](https://github.com/ReactiveX/RxJava)
* [Dagger2](https://github.com/google/dagger)
* [Retrofit2](https://github.com/square/retrofit)
* [Timber](https://github.com/JakeWharton/timber)

## Code Style
This project uses [ktlint](https://github.com/shyiko/ktlint) for linting.

#### Linting
```sh
./gradlew ktlint		# lint check
./gradlew ktlintFormat	# lint check format
```

## Changelog
A changelog in the [keep-a-changelog](https://keepachangelog.com/en/1.0.0/) format is available [here](https://github.com/sphrak/krisinformation/blob/master/app/src/main/res/raw/changelog.md).

## Code Contribution Guidelines
If you would like to contribute code to the project fork the project and find an issue/feature you would like to work on. Ideally check with a maintainer so you dont work on something that might be in the workings already.

Your pull request will be failed by the build server if it does not have passing unittests and lintchecks. A build can also be failed if you decrease the testing coverage.

When submitting a pull request make sure you squash
the commit(s) for that PR -- Do this so we can keep a clean
git history.

* [More information here](https://github.com/sphrak/krisinformation/blob/master/CONTRIBUTING.md)

## Localization
If you feel this app could use a new language feel free to contribute. 
After your translation(s) has been approved, feel free to submit a pull request with your
name added to `TRANSLATORS` file.


## License

	Copyright 2018 Niclas Kron

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
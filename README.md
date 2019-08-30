[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

**deprecated, feed got integrated into the [vädret app](https://github.com/vadret/android) instead.**

# Krisinformation
**Krisinformation** is a Swedish emergency information app powered by Swedish Civil Contingencies Agency. The app gets
data from [Krisinformation](https://www.krisinformation.se/english). That site in turn gets data from several other
Swedish authorities such as [Myndigheten för Samhällsskydd och Beredskap](https://www.msb.se/sv/Produkter--tjanster/Oppna-data/) 
and [SMHI](https://www.smhi.se/) among others. API documentation in Swedish is available [here](https://www.krisinformation.se/om-krisinformation/oppen-data).

## Krisinformation API

* [API Endpoints](https://api.krisinformation.se/)
* [Links data](https://api.krisinformation.se/v1/links?format=json)
* [CapMessage data](https://api.krisinformation.se/v1/capmessage?format=json)
* [Feed data](https://api.krisinformation.se/v1/feed?format=json)
* [Theme data](https://api.krisinformation.se/v1/themes?format=json)

## Changelog
A changelog in the [keep-a-changelog](https://keepachangelog.com/en/1.0.0/) format is available [here](https://github.com/sphrak/krisinformation/blob/master/app/src/main/res/raw/changelog.md).

## Code Contribution Guidelines
If you would like to contribute code to the project fork the project and find an issue/feature you would like to work on. Ideally check with a maintainer so you dont work on something that might be in the workings already.

Your pull request will be failed by the build server if it does not have passing unittests and lintchecks. A build can also be failed if you decrease the testing coverage.

When submitting a pull request make sure you squash
the commit(s) for that PR -- Do this so we can keep a clean
git history.

* [More information here](https://github.com/sphrak/krisinformation/blob/master/CONTRIBUTING.md)

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

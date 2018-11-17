package fi.kroon.krisinformation.data.theme.exception

import fi.kroon.krisinformation.data.exception.Failure

class ThemeFailure {

    class NoThemeAvailableFailure : Failure.FeatureFailure()
}
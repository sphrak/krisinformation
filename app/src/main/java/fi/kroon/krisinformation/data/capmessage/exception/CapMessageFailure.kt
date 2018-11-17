package fi.kroon.krisinformation.data.capmessage.exception

import fi.kroon.krisinformation.data.exception.Failure

class CapMessageFailure {

    class NoCapMessageAvailable : Failure.FeatureFailure()
}
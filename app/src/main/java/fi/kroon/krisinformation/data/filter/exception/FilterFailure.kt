package fi.kroon.krisinformation.data.filter.exception

import fi.kroon.krisinformation.data.exception.Failure

class FilterFailure {

    class FilteringFailed : Failure.FeatureFailure()
    class DatabaseInsertFailed : Failure.FeatureFailure()
}
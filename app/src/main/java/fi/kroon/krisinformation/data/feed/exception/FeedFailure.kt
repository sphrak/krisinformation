package fi.kroon.krisinformation.data.feed.exception

import fi.kroon.krisinformation.data.exception.Failure

class FeedFailure {

    class NoFeedAvailable : Failure.FeatureFailure()
}
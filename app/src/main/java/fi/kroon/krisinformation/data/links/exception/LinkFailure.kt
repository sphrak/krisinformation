package fi.kroon.krisinformation.data.links.exception

import fi.kroon.krisinformation.data.exception.Failure

class LinkFailure {

    class NoLinkAvailableFailure : Failure.FeatureFailure()
}
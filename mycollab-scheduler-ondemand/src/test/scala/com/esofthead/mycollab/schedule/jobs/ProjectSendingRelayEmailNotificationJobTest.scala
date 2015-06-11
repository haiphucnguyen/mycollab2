package com.esofthead.mycollab.schedule.jobs

import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class ProjectSendingRelayEmailNotificationJobTest extends FlatSpec with Matchers with MockFactory {
    "This test" should "pass" in {
        true should be === true
    }
    List(1,2,3,4) should have length(4)
}

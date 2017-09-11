package com.mycollab.module.tracker.service

import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.StringSearchField
import com.mycollab.module.tracker.domain.SimpleVersion
import com.mycollab.module.tracker.domain.Version
import com.mycollab.module.tracker.domain.criteria.VersionSearchCriteria
import com.mycollab.test.DataSet
import com.mycollab.test.spring.IntegrationServiceTest
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.GregorianCalendar

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple

@RunWith(SpringJUnit4ClassRunner::class)
class VersionServiceTest : IntegrationServiceTest() {

    @Autowired
    private val versionService: VersionService? = null

    private val criteria: VersionSearchCriteria
        get() {
            val criteria = VersionSearchCriteria()
            criteria.saccountid = NumberSearchField(1)
            criteria.projectId = NumberSearchField(1)
            return criteria
        }

    @DataSet
    @Test
    @Throws(ParseException::class)
    fun testGetListVersions() {
        val versions = versionService!!.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleVersion>

        assertThat(versions.size).isEqualTo(4)
        assertThat<SimpleVersion>(versions).extracting("id", "description", "status",
                "name", "numBugs", "numOpenBugs", "duedate",
                "createdtime").contains(
                tuple(4, "Version 4.0.0", "Open", "4.0.0", 0, 0, dateformat.parse("2014-09-17 10:10:10"), dateformat.parse("2014-09-10 10:10:10")),
                tuple(3, "Version 3.0.0", "Closed", "3.0.0", 1, 1, dateformat.parse("2014-09-15 10:10:10"), dateformat.parse("2014-08-10 10:10:10")),
                tuple(2, "Version 2.0.0", "Closed", "2.0.0", 2, 1, dateformat.parse("2014-09-12 10:10:10"), dateformat.parse("2014-07-10 10:10:10")),
                tuple(1, "Version 1.0.0", "Open", "1.0.0", 1, 1, dateformat.parse("2014-09-10 10:10:10"), dateformat.parse("2014-06-10 10:10:10")))
    }

    @DataSet
    @Test
    fun testTotalCount() {
        val versions = versionService!!.findPageableListByCriteria(BasicSearchRequest(criteria))
        assertThat(versions.size).isEqualTo(4)
    }

    @DataSet
    @Test
    fun testFindVersionById() {
        val criteria = VersionSearchCriteria()
        criteria.id = NumberSearchField(1)

        val versions = versionService!!.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleVersion>
        assertThat(versions.size).isEqualTo(1)
        assertThat<SimpleVersion>(versions).extracting("id", "description", "status",
                "name", "numBugs", "numOpenBugs").contains(
                tuple(1, "Version 1.0.0", "Open", "1.0.0", 1, 1))
    }

    @DataSet
    @Test
    fun testFindByCriteria() {
        val criteria = criteria
        criteria.id = NumberSearchField(2)
        criteria.status = StringSearchField.and("Closed")
        criteria.versionname = StringSearchField.and("2.0.0")

        val versions = versionService!!.findPageableListByCriteria(BasicSearchRequest(criteria)) as List<SimpleVersion>
        assertThat(versions.size).isEqualTo(1)
        assertThat<SimpleVersion>(versions).extracting("id", "description", "status",
                "name", "numBugs", "numOpenBugs").contains(
                tuple(2, "Version 2.0.0", "Closed", "2.0.0", 2, 1))
    }

    @DataSet
    @Test
    fun testSaveVersion() {
        val version = Version()
        version.projectid = 1
        version.duedate = GregorianCalendar(2014, 10, 6).time
        version.name = "sss"
        version.createduser = "hai79"
        version.saccountid = 1
        version.description = "a"
        version.status = "Open"

        val versionId = versionService!!.saveWithSession(version, "hai79")
        assertThat(versionId > 0).isEqualTo(true)
    }

    companion object {

        private val dateformat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    }
}

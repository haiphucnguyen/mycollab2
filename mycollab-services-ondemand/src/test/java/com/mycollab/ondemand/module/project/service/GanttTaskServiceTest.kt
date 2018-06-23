package com.mycollab.ondemand.module.project.service

import com.google.common.collect.Collections2
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.*
import com.mycollab.module.project.service.GanttAssignmentService
import com.mycollab.ondemand.test.spring.IntegrationServiceTest
import com.mycollab.test.DataSet
import org.assertj.core.groups.Tuple
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import org.assertj.core.api.Assertions.assertThat

@RunWith(SpringJUnit4ClassRunner::class)
class GanttTaskServiceTest : IntegrationServiceTest() {
    @Autowired
    private lateinit var ganttAssignmentService: GanttAssignmentService

    @Test
    @DataSet
    fun testGetGanttAssignments() {
        val assignmentWithPredecessors = ganttAssignmentService.getTaskWithPredecessors(listOf(1), 1)
        assertThat(assignmentWithPredecessors).hasSize(1)

        val project = assignmentWithPredecessors[0] as ProjectGanttItem
        assertThat(project.name).isEqualTo("Project A")
        assertThat(project.type).isEqualTo(ProjectTypeConstants.PROJECT)

        val subMilestones = project.milestones!!
        assertThat(subMilestones).hasSize(2)

        assertThat(subMilestones).extracting("name").contains("Milestone 1", "Milestone 2")

        val milestoneFilterResult = Collections2.filter(subMilestones) { "Milestone 2" == it!!.name }
        assertThat(milestoneFilterResult).hasSize(1)
        val milestone2 = milestoneFilterResult.iterator().next()
        val subTasks = milestone2.subTasks!!
        assertThat(subTasks).hasSize(2)
        assertThat(subTasks).extracting("name", "id").contains(Tuple.tuple("Task 3", 3), Tuple.tuple("Task 4", 4))

        val taskFilterResult = Collections2.filter(subTasks) { "Task 4" == it!!.name }

        assertThat(taskFilterResult).hasSize(1)
        val task = taskFilterResult.iterator().next()
        assertThat(task.subTasks).hasSize(1)

        val task5 = task.subTasks!![0]
        assertThat(task5.name).isEqualTo("Task 5")
        assertThat(task5.progress).isEqualTo(10.0)
        assertThat(task5.predecessors).hasSize(1)
        val predecessor = task5.predecessors!![0]
        assertThat(predecessor.descid).isEqualTo(2)

        val milestone1 = Collections2.filter(subMilestones) { "Milestone 1" == it!!.name }.iterator().next()
        val task2 = Collections2.filter(milestone1.subTasks!!) { "Task 2" == it!!.name }.iterator().next()
        val dependents = task2.dependents
        assertThat(dependents).hasSize(1)

        val tasksWithNoMilestones = project.tasksWithNoMilestones
        assertThat(tasksWithNoMilestones).hasSize(1)
    }
}

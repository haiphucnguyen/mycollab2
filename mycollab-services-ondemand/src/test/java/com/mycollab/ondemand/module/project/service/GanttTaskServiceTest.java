package com.mycollab.ondemand.module.project.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.service.GanttAssignmentService;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class GanttTaskServiceTest extends IntegrationServiceTest {
    @Autowired
    private GanttAssignmentService ganttAssignmentService;

    @Test
    @DataSet
    public void testGetGanttAssignments() {
        List<AssignWithPredecessors> assignmentWithPredecessors = ganttAssignmentService.getTaskWithPredecessors(Collections.singletonList(1), 1);
        assertThat(assignmentWithPredecessors).hasSize(1);

        ProjectGanttItem project = (ProjectGanttItem) assignmentWithPredecessors.get(0);
        assertThat(project.getName()).isEqualTo("Project A");
        assertThat(project.getType()).isEqualTo(ProjectTypeConstants.PROJECT);

        List<MilestoneGanttItem> subMilestones = project.getMilestones();
        assertThat(subMilestones).hasSize(2);

        assertThat(subMilestones).extracting("name").contains("Milestone 1", "Milestone 2");

        Collection<MilestoneGanttItem> milestoneFilterResult = Collections2.filter(subMilestones, new Predicate<MilestoneGanttItem>() {
            @Override
            public boolean apply(MilestoneGanttItem milestone) {
                return "Milestone 2".equals(milestone.getName());
            }
        });
        assertThat(milestoneFilterResult).hasSize(1);
        MilestoneGanttItem milestone2 = milestoneFilterResult.iterator().next();
        final List<TaskGanttItem> subTasks = milestone2.getSubTasks();
        assertThat(subTasks).hasSize(2);
        assertThat(subTasks).extracting("name", "id").contains(Tuple.tuple("Task 3", 3), Tuple.tuple("Task 4", 4));

        Collection<TaskGanttItem> taskFilterResult = Collections2.filter(subTasks, task -> "Task 4".equals(task.getName()));

        assertThat(taskFilterResult).hasSize(1);
        TaskGanttItem task = taskFilterResult.iterator().next();
        assertThat(task.getSubTasks()).hasSize(1);

        TaskGanttItem task5 = task.getSubTasks().get(0);
        assertThat(task5.getName()).isEqualTo("Task 5");
        assertThat(task5.getProgress()).isEqualTo(10);
        assertThat(task5.getPredecessors()).hasSize(1);
        TaskPredecessor predecessor = task5.getPredecessors().get(0);
        assertThat(predecessor.getDescid()).isEqualTo(2);

        MilestoneGanttItem milestone1 = Collections2.filter(subMilestones, input -> "Milestone 1".equals(input.getName())).iterator().next();
        TaskGanttItem task2 = Collections2.filter(milestone1.getSubTasks(), input -> "Task 2".equals(input.getName())).iterator().next();
        List<TaskPredecessor> dependents = task2.getDependents();
        assertThat(dependents).hasSize(1);

        List<TaskGanttItem> tasksWithNoMilestones = project.getTasksWithNoMilestones();
        assertThat(tasksWithNoMilestones).hasSize(1);
    }
}

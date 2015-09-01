package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors;
import com.esofthead.mycollab.module.project.domain.MilestoneGanttItem;
import com.esofthead.mycollab.module.project.domain.ProjectGanttItem;
import com.esofthead.mycollab.module.project.domain.TaskGanttItem;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class GanttTaskServiceTest extends IntergrationServiceTest {
    @Autowired
    private GanttAssignmentService ganttAssignmentService;

    @Test
    @DataSet
    public void testGetGanttAssignments() {
        List<AssignWithPredecessors> assignmentWithPredecessors = ganttAssignmentService.getTaskWithPredecessors(Arrays.asList(1), 1);
        assertThat(assignmentWithPredecessors).hasSize(1);

        ProjectGanttItem project = (ProjectGanttItem) assignmentWithPredecessors.get(0);
        assertThat(project.getName()).isEqualTo("Project A");
        assertThat(project.getType()).isEqualTo(ProjectTypeConstants.PROJECT);

        List<MilestoneGanttItem> subMilestones = project.getSubTasks();
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

        Collection<TaskGanttItem> taskFilterResult = Collections2.filter(subTasks, new Predicate<TaskGanttItem>() {
            @Override
            public boolean apply(TaskGanttItem task) {
                return "Task 4".equals(task.getName());
            }
        });

        assertThat(taskFilterResult).hasSize(1);
        TaskGanttItem task = taskFilterResult.iterator().next();
        assertThat(task.getSubTasks()).hasSize(1);

        List<TaskGanttItem> tasksWithNoMilestones = project.getTasksWithNoMilestones();
        assertThat(tasksWithNoMilestones).hasSize(1);
    }
}

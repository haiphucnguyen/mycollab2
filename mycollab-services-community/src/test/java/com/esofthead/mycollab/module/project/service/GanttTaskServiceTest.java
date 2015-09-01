package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors;
import com.esofthead.mycollab.module.project.domain.MilestoneGanttItem;
import com.esofthead.mycollab.module.project.domain.ProjectGanttItem;
import com.esofthead.mycollab.module.project.domain.TaskGanttItem;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
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

        List<TaskGanttItem> tasksWithNoMilestones = project.getTasksWithNoMilestones();
        assertThat(tasksWithNoMilestones).hasSize(1);
    }
}

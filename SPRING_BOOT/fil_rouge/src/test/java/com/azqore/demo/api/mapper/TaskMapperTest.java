package com.azqore.demo.api.mapper;

import com.azqore.demo.api.dto.TaskDto;
import com.azqore.demo.api.dto.UserDto;
import com.azqore.demo.entity.PriorityTask;
import com.azqore.demo.entity.Task;
import com.azqore.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    void toDto_should_map_task_entity_to_task_dto() {
        // GIVEN
        Task taskEntity =new Task();
        taskEntity.setId(100L);
        taskEntity.setDescription("MA_DESCRIPTION");
        taskEntity.setDueDate(LocalDate.of(2024,1,1));
        taskEntity.setDone(true);
        taskEntity.setPriority(PriorityTask.MEDIUM);
        taskEntity.setAssignedUser(new User());
        when(userMapper.toDto(any(User.class))).thenReturn(any(UserDto.class));
        // WHEN
        TaskDto output = taskMapper.toDto(taskEntity);
        // THEN
        assertEquals(100L, output.getId());
        assertEquals("MA_DESCRIPTION", output.getDescription());
        assertEquals(LocalDate.of(2024,1,1), output.getDueDate());
        assertTrue(output.isDone());
        assertEquals(PriorityTask.MEDIUM, output.getPriority());
        verify(userMapper, times(1)).toDto(any(User.class));
    }


    @Test
    void fromDto() {


    }


}
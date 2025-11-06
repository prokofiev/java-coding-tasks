import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskQueueServiceTest {

  private TaskQueueService taskService;

  @BeforeEach
  void setUp() {
    taskService = new TaskQueueService();
  }

  @Test
  void shouldAddTaskWithValidData() {
    // given
    Task task = new Task("1", "Test Task", 2, LocalDateTime.now());

    // when
    taskService.addTask(task);

    // then
    assertEquals(1, taskService.getTaskCount());
  }

  @Test
  void shouldThrowExceptionWhenInvalidPriority() {
    // given
    Task task = new Task("1", "Test Task", 6, LocalDateTime.now());

    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      taskService.addTask(task);
    });
  }

  @Test
  void shouldReturnTasksInPriorityOrder() {
    // given
    Task lowPriority = new Task("1", "Low", 5, LocalDateTime.now());
    Task highPriority = new Task("2", "High", 1, LocalDateTime.now());
    Task mediumPriority = new Task("3", "Medium", 3, LocalDateTime.now());

    // when
    taskService.addTask(lowPriority);
    taskService.addTask(highPriority);
    taskService.addTask(mediumPriority);

    List<Task> tasks = taskService.getAllTasks();

    // then
    assertEquals("2", tasks.get(0).getId()); // Highest priority first
    assertEquals("3", tasks.get(1).getId());
    assertEquals("1", tasks.get(2).getId());
  }

  @Test
  void shouldGetNextTaskWithHighestPriority() {
    // given
    taskService.addTask(new Task("1", "Low", 3, LocalDateTime.now()));
    taskService.addTask(new Task("2", "High", 1, LocalDateTime.now()));

    // when
    Task nextTask = taskService.getNextTask();

    // then
    assertEquals("2", nextTask.getId());
    assertEquals(1, taskService.getTaskCount()); // One task should remain
  }

  @Test
  void shouldThrowExceptionWhenGettingFromEmptyQueue() {
    // when & then
    assertThrows(IllegalStateException.class, () -> {
      taskService.getNextTask();
    });
  }

  @Test
  void shouldRemoveExistingTask() {
    // given
    taskService.addTask(new Task("1", "Task 1", 1, LocalDateTime.now()));
    taskService.addTask(new Task("2", "Task 2", 2, LocalDateTime.now()));

    // when
    boolean removed = taskService.removeTask("1");

    // then
    assertTrue(removed);
    assertEquals(1, taskService.getTaskCount());
  }

  @Test
  void shouldNotRemoveNonExistingTask() {
    // given
    taskService.addTask(new Task("1", "Task 1", 1, LocalDateTime.now()));

    // when
    boolean removed = taskService.removeTask("999");

    // then
    assertFalse(removed);
    assertEquals(1, taskService.getTaskCount());
  }
}
package com.tazz.adventofcode.y2015.day17.producer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.LineFileReaderImpl;
import com.tazz.adventofcode.y2015.day17.shared.Task;

import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private TaskProducer taskProducer;

    private ParserContext<Integer> parserContext;

    public TaskService(TaskProducer taskProducer) {
        this.taskProducer = taskProducer;
        parserContext = new ParserContext<>(new ContainerListParser(), new LineFileReaderImpl());
    }

    public void loadContainers(String filepath, int targetVolume) {
        Mono<List<Integer>> containers = parserContext.parseClasspathFile(filepath).collectList();
        containers.subscribe(containerList -> {
            Task task = new Task(targetVolume, containerList);
            taskProducer.sendMessage(task);
        });
    }
}

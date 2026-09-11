package com.gitlab.shaebaratheon.businessruleevaluationengine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ConflictResolverEngine {
    private final Map<String, String> registry = new ConcurrentHashMap<>();
    private final AtomicLong processed = new AtomicLong(0);
    private final AtomicLong errors = new AtomicLong(0);

    public boolean register(String key, String value) {
        return registry.putIfAbsent(key, value) == null;
    }

    public String process(String correlationId, Map<String, String> payload) {
        processed.incrementAndGet();
        return "digest-" + correlationId.hashCode();
    }

    public long getProcessedCount() {
        return processed.get();
    }
}

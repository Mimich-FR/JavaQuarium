/*
 * This file is part of JavaQuarium, licensed under the MIT License.
 *
 *  Copyright (c)  Mimich
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.mimich.javaquarium.utils;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple ThreadFactory builder implementation.
 * Inspired by the Guava ThreadFactoryBuilder.
 *
 * Author MrMicky
 *
 */

public final class ThreadFactoryBuilder implements ThreadFactory {

    private final AtomicInteger threadCount = new AtomicInteger();

    private final ThreadFactory originalThreadFactory;

    private String name;

    private int priority;

    private boolean daemon;

    public ThreadFactoryBuilder() {
        this(Executors.defaultThreadFactory());
    }

    public ThreadFactoryBuilder(ThreadFactory threadFactory) {
        this.originalThreadFactory = threadFactory;
    }

    public ThreadFactoryBuilder name(String name) {

        this.name = Objects.requireNonNull(name, "name");

        return this;

    }

    public ThreadFactoryBuilder priority(int priority) {

        if (priority > Thread.MAX_PRIORITY || priority < Thread.MIN_PRIORITY) {

            throw new IllegalArgumentException("Priority must be between " + Thread.MIN_PRIORITY + " and " + Thread.MAX_PRIORITY + " : " + priority);

        }

        this.priority = priority;

        return this;
    }

    public final ThreadFactoryBuilder daemon(boolean daemon) {

        this.daemon = daemon;

        return this;

    }

    public final ThreadFactoryBuilder daemon() {

        return daemon(true);

    }

    @Override
    public Thread newThread(Runnable runnable) {

        Thread thread = originalThreadFactory.newThread(runnable);

        if (name != null) {

            thread.setName(name.replace("%t", Integer.toString(threadCount.getAndIncrement())));

        }

        if (priority > 0) {

            thread.setPriority(priority);

        }

        if (daemon) {

            thread.setDaemon(true);

        }

        return thread;

    }

}
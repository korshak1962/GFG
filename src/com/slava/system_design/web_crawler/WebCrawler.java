package com.slava.system_design.web_crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class WebCrawler {
    public static final int N_THREADS = Runtime.getRuntime().availableProcessors() - 1;
    ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);    // Core interfaces to implement:

    BlockingDeque<String> urlsToCrawl = new LinkedBlockingDeque<>();
    ConcurrentMap<String, Boolean> foundUrls = new ConcurrentHashMap<>();

    public void addSeedUrl(String url) {
        if (foundUrls.putIfAbsent(url, true) == null) urlsToCrawl.add(url);
    }

    public void startCrawling() {
        for (int i = 0; i < N_THREADS; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    while (!Thread.interrupted()) crawl(urlsToCrawl.takeLast());
                } catch (InterruptedException e) {
                    return;
                }
            }, executor);
        }
    }

    private void crawl(String url) {
        if (url == null || url.isEmpty()) return;
        //curl get content by url
        String content = "";  // just for example
        for (String newFoundUrl : parse(content)) {
            if (foundUrls.putIfAbsent(newFoundUrl, true) == null) urlsToCrawl.addFirst(newFoundUrl);
        }
    }

    public void stopCrawling() {
        executor.shutdownNow();
    }

    public int getStats() {
        // found foundUrls
        return foundUrls.size();
    }

    // Key components to design:
    interface URLFrontier { /* URL queue management */
    }

    interface Politeness { /* Rate limiting per domain */
    }

    private List<String> parse(String content) {
        /// parse
        return new ArrayList<>();
    }
}
package com.codevictims.bayt.maintenance.service;
import java.time.Clock;
import java.util.*;
import org.springframework.stereotype.Component;
@Component
public class AiRequestLimiter {
 private record Window(long start,int count) {}
 private final Map<Long,Window> windows=new HashMap<>();
 private final Clock clock;
 public AiRequestLimiter(Clock clock){this.clock=clock;}
 public synchronized boolean allow(Long actor){
  long now=clock.millis();
  windows.entrySet().removeIf(e->now-e.getValue().start()>=60000);
  Window w=windows.get(actor);
  if(w==null){if(windows.size()>=10000)return false;windows.put(actor,new Window(now,1));return true;}
  if(w.count()>=10)return false;
  windows.put(actor,new Window(w.start(),w.count()+1));return true;
 }
}

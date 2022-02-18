package com.kataer.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
@Slf4j
@Data
public class FilterB implements Filter {
  private static final String name = "FilterB";

  @Override
  public void doFilter(Request req, Response resp, FilterChain chain) {
    log.info("after :{}", name);
    chain.doFilter(req, resp);
    log.info("after :{}", name);
  }
}

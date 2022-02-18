package com.kataer.filter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
@Data
public class FilterChainImpl implements FilterChain {
  /**
   * 该属性是否应该为类变量
   */
  protected static List<Filter> filters = new ArrayList<>();
  private int pos;
  private boolean hasService;

  static {
    filters.add(new FilterA());
    filters.add(new FilterB());
  }

  @Override
  public void doFilter(Request req, Response resp) {
    Filter nextFilter = getNextFilter();
    if (nextFilter != null) {
      nextFilter.doFilter(req, resp, this);
    }
  }

  private Filter getNextFilter() {
    if (pos <= filters.size() - 1) {
      return filters.get(pos++);
    }
    return null;
  }
}

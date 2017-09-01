
package com.test;

public class HistorySearchRequest extends CommonStetRequest {

  private CommonHistorySearchAttributes commonAttributes;

  private String valReq;

  public HistorySearchRequest() {
    this.commonAttributes = new CommonHistorySearchAttributes();
  }

  /**
   * @return the commonAttributes
   */
  public CommonHistorySearchAttributes getCommonAttributes() {
    return commonAttributes;
  }

  /**
   * @param commonAttributes
   *          the commonAttributes to set
   */
  public void setCommonAttributes(CommonHistorySearchAttributes commonAttributes) {
    this.commonAttributes = commonAttributes;
  }

  /**
   * @return the valReq
   */
  public String getValReq() {
    return valReq;
  }

  /**
   * @param valReq
   *          the valReq to set
   */
  public void setValReq(String valReq) {
    this.valReq = valReq;
  }

}

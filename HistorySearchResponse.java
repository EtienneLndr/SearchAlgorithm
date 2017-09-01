
package com.test;

public class HistorySearchResponse extends CommonStetResponse {

  private CommonHistorySearchAttributes commonAttributes;

  private String valResp;

  public HistorySearchResponse() {
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
   * @return the valResp
   */
  public String getValResp() {
    return valResp;
  }

  /**
   * @param valResp
   *          the valResp to set
   */
  public void setValResp(String valResp) {
    this.valResp = valResp;
  }

}

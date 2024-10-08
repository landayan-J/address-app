package dev.ko.libs.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateXMLAdapter extends XmlAdapter<String, LocalDate>{

  @Override
  public LocalDate unmarshal(String v) throws Exception {
    return LocalDate.parse(v);
  }

  @Override
  public String marshal(LocalDate v) throws Exception {
    return v.toString();
  }
}
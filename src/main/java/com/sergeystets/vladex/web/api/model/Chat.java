package com.sergeystets.vladex.web.api.model;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Chat {

  private long id;
  private String name;
  private List<Contact> members;
  private boolean peerToPeer;
}

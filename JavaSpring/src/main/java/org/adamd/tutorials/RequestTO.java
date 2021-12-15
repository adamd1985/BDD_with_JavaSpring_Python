package org.adamd.tutorials;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestTO {
    Integer num1;
    Integer num2;
}

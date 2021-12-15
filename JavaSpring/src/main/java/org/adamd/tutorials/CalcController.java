package org.adamd.tutorials;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the asynch calculator.
 */

@RestController
@Log
public final class CalcController {
    private final AsyncCalculator calc = new AsyncCalculator();

    @PostMapping("/calculation/addition/{user}")
    public void startAddition(@PathVariable String user, @RequestBody RequestTO req) {
        log.fine("startAddition");
        calc.requestAddition(user, req.getNum1(), req.getNum2());
    }

    @GetMapping("/calculation/addition/{user}")
    public Integer getLastAdditionResult(@PathVariable String user) {
        log.fine("getLastAdditionResult");
        return calc.getLastCalculation(user);
    }

    @GetMapping("/calculation/addition/")
    public Integer heartBeat() {
        log.fine("heartBeat");
        return 1;
    }
}

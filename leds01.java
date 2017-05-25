package Raspberri_pi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/*
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;
*/

/**
 * This example code demonstrates how to perform simple state control of a GPIO
 * pin on the Raspberry Pi.
 *
 * @author Robert Savage
 */
public class leds {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("<--Pi4J--> GPIO Control Example ... started.");

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin (NUMBER OF PIN NOT GPIO) as an output pin and turn
		// on
		final GpioPinDigitalOutput pin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLED", PinState.HIGH);
		final GpioPinDigitalOutput pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "MyLED", PinState.HIGH);
		final GpioPinDigitalOutput pin13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyLED", PinState.HIGH);

		// set shutdown state for this pin
		pin07.setShutdownOptions(true, PinState.LOW);
		pin11.setShutdownOptions(true, PinState.LOW);
		pin13.setShutdownOptions(true, PinState.LOW);

		System.out.println("--> GPIO state should be: ON");

		Thread.sleep(5000);

		// turn off // AÑADIR OTROS 2 LEDS MAÑANA //
		pin07.low();
		System.out.println("--> GPIO state should be: OFF");

		Thread.sleep(5000);

		// toggle the current state of gpio (should turn on)
		pin07.toggle();
		System.out.println("--> GPIO state should be: ON");

		Thread.sleep(5000);

		// toggle the current state of gpio (should turn off)
		pin07.toggle();
		System.out.println("--> GPIO state should be: OFF");

		Thread.sleep(5000);

		// turn on gpio for 1 second and then off
		System.out.println("--> GPIO state should be: ON for only 1 second");
		pin07.pulse(1000, true); // set second argument to 'true' use a blocking
									// call

		// stop all GPIO activity/threads by shutting down the GPIO controller
		// (this method will forcefully shutdown all GPIO monitoring threads and
		// scheduled tasks)
		gpio.shutdown();

		System.out.println("Exiting ControlGpioExample");
	}
}

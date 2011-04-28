/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Franck Fleurey and Brice Morin
 * Company: SINTEF IKT, Oslo, Norway
 * Date: 2011
 */
package org.sintef.jarduino.examples;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.InterruptPin;
import org.sintef.jarduino.InterruptTrigger;
import org.sintef.jarduino.JArduino;
import org.sintef.jarduino.PinMode;
/*
This example shows how to use external interrupt of the
arduino board.
*/
public class SimpleInterrupt extends JArduino {

	final DigitalPin ledPin = DigitalPin.PIN_9; // Analog output pin that the LED is attached to
	
	public SimpleInterrupt(String port) {
		super(port);
	}

	@Override
	protected void setup() {
		// initialize the digital pin as an output.
		// Pin 13 has an LED connected on most Arduino boards:
		pinMode(ledPin, PinMode.OUTPUT);
		// with a button connected on pin2, this will generate an
		// interrupt when the button is pressed
		attachInterrupt(InterruptPin.PIN_2_INT0, InterruptTrigger.CHANGE);
	}

	@Override
	protected void loop() {
		// Do Nothing
		delay(1000); // wait for a second
	}

	
	@Override
	protected void interrupt0() {
		// This operation gets invoked when an INT0 is generated
		DigitalState button_state = digitalRead(DigitalPin.PIN_2);
		System.out.println("INT0: Toggling the LED on " + ledPin + " button state is " + button_state);
		digitalWrite(ledPin, button_state);
	}

	public static void main(String[] args) {
		JArduino arduino = new SimpleInterrupt("COM21");
		arduino.runArduinoProcess();
	}

}

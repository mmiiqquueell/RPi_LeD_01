import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class led {

	public static void main(String[] args) throws InterruptedException {

		// crear controlador gpio //
		final GpioController gpioControlador = GpioFactory.getInstance();

		// Control de estado gpio //
		int rojo = 0, amarillo = 0, verde = 0, seleccion;
		boolean r = false, a = false, v = false;

		// Selección de controlador GPIO (PIN) temporal
		final GpioPinDigitalOutput pin07 = gpioControlador.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLEDGreen",
				PinState.LOW);
		final GpioPinDigitalOutput pin11 = gpioControlador.provisionDigitalOutputPin(RaspiPin.GPIO_11, "MyLEDYellow",
				PinState.LOW);
		final GpioPinDigitalOutput pin13 = gpioControlador.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyLEDRed",
				PinState.LOW);

		// Asignar la posibilidad de desactivar el GPIO (PIN)
		pin07.setShutdownOptions(true, PinState.LOW);
		pin11.setShutdownOptions(true, PinState.LOW);
		pin13.setShutdownOptions(true, PinState.LOW);

		Scanner entrada = new Scanner(System.in);
		boolean dentro = true;
		while (dentro) {
			System.out.println("\nControlador de LEDS: \n 1 = VERDE / 2 = AMARILLO / 3 = ROJO / 4 = SALIR");
			seleccion = entrada.nextInt();
			switch (seleccion) {
			case 1:
				System.out.println("Control LED VERDE: \n 1 = Encender / 0 = Apagar");
				verde = entrada.nextInt();
				switch (verde) {
				case 0:
					if (v == true) {
						pin07.toggle();
						System.out.println("LED VERDE: Apagado");
						v = false;
						break;
					} else if (v == false) {
						System.out.println("LED VERDE: Ya está apagado!");
						break;
					}
				case 1:
					if (v == false) {
						pin07.toggle();
						System.out.println("LED VERDE: Encendido");
						v = true;
						break;
					} else if (v == true) {
						System.out.println("LED VERDE: Ya está encendido!");
						break;
					}

				default:
					System.out.println("Regresando al menú principal");
					break;
				}
				break;
			case 2:
				System.out.println("Control LED AMARILLO: \n 1 = Encender / 0 = Apagar");
				amarillo = entrada.nextInt();
				switch (amarillo) {
				case 0:
					if (a == true) {
						pin11.toggle();
						System.out.println("LED AMARILLO: Apagado");
						a = false;
						break;
					} else if (a == false) {
						System.out.println("LED AMARILLO: Ya está apagado!");
						break;
					}

				case 1:
					if (a == false) {
						pin11.toggle();
						System.out.println("LED AMARILLO: Encendido");
						a = true;
						break;
					} else if (a == true) {
						System.out.println("LED AMARILLO: Ya está encendido!");
						break;
					}

				default:
					System.out.println("Regresando al menú principal");
					break;
				}
				break;
			case 3:
				System.out.println("Control LED ROJO: \n 1 = Encender / 0 = Apagar");
				rojo = entrada.nextInt();
				switch (rojo) {
				case 0:
					if (r == true) {
						pin13.toggle();
						System.out.println("LED ROJO: Apagado");
						r = false;
						break;
					} else if (r == false) {
						System.out.println("LED ROJO: Ya está apagado!");
						break;
					}

				case 1:
					if (r == false) {
						pin13.toggle();
						System.out.println("LED ROJO: Encendido");
						r = true;
						break;
					} else if (r == true) {
						System.out.println("LED ROJO: Ya está encendido!");
						break;
					}

				default:
					System.out.println("Regresando al menú principal");
					break;
				}
				break;
			default:
				System.out.println("\n\nDesconectando sistema...");
				gpioControlador.shutdown();
				System.out.println("\n\nRegresando a terminal. \n \n \n \n \n \n \n \n ");
				dentro = false;
				break;
			}
		}
	}
}

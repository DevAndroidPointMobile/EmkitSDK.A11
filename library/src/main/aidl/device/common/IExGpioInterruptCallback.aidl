package device.common;

oneway interface IExGpioInterruptCallback {
	void onChanged(in int gpio);
}

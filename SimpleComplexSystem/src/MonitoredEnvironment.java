import java.util.Random;

public class MonitoredEnvironment {
	//Environment name
	private String name;
	
	//Water outflow
	private int waterOutflow;
	
	//Gas outflow
	private int gasOutflow;
	
	//Environment temperature
	private int temperature;
	
	public void setEnvironmentName(String name) {
		this.name = name;
	}

	public void setWaterOutflow(int waterOutflow) {
		this.waterOutflow = waterOutflow;
	}//End method setWaterOutflw
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}//End method setTemperature
	
	public void setGasOutflow(int gasOutflow) {
		this.gasOutflow = gasOutflow;
	}//End method setGasOutflow
	
	public int getTemperature() {
		return temperature;
	}//End method getTemperature
	
	public int getGasOutflow() {
		return gasOutflow;
	}//End method getGasOutflow
	
	public int getWaterOutflow() {
		return waterOutflow;
	}//End method getWaterOutflow
	
	public double waterVirtualSensor() {
		Random measuredValue = new Random();
		return measuredValue.nextInt(getWaterOutflow())
				+ getWaterOutflow() - (getWaterOutflow() * 0.05);
	}//End method waterVirtualSensor
	
	public double gasVirtualSensor() {
		Random measuredValue = new Random();
		return measuredValue.nextInt(getGasOutflow())
				+ getGasOutflow() - (getGasOutflow() * 0.05);
	}//End method gasVirtualSensor
	
	public double temperatureVirtualSensor() {
		Random measuredValue = new Random();
		return measuredValue.nextInt(getTemperature())
				+ getTemperature() - (getTemperature() * 0.05);
	}//End method temperatureVirtualSensor
	
	public boolean gasSystem() {
		if(gasVirtualSensor() < getGasOutflow()) {
			return false;
		}
		return true;
	}//End method gasSystem
	
	public boolean waterSystem() {
		if(waterVirtualSensor() < getWaterOutflow()) {
			return false;
		}
		return true;
	}//End method waterSystem
	
	public boolean temperatureSystem() {
		if(temperatureVirtualSensor() < getTemperature()) {
			return false;
		}
		return true;
	}//End method temperatureSystem
}//End class MonitoredEnvironment
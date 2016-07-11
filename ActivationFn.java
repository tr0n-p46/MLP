package Perceptron;

public class ActivationFn 
{
	private int activationFn;
	
	public ActivationFn(int activationFn)
	{
		this.activationFn = activationFn;
	}
	
	public float activate(float input)
	{
		switch(activationFn)
		{
		case 1: return LinearTransfer(input);
		case 2: return Sigmoidal(input);
		case 3: return Threshold(input);
		}
		return 0.0f;
	}
	
	public float activateDerivative(float input)
	{
		switch(activationFn)
		{
		case 1: return dLinearTransfer(input);
		case 2: return dSigmoidal(input);
		case 3: return dThreshold(input);
		}
		return 0.0f;
	}
	
	public float dLinearTransfer(float value)
	{
		return 1.0f;
	}
	
	public float dSigmoidal(float value)
	{
		return value * (1 - value);
	}
	
	public float dThreshold(float input)
	{
		return 0.0f;
	}
	
	public float LinearTransfer(float value)
	{
		return value;
	}
	
	public float Sigmoidal(float value)
	{
		return (float)(1/(1 + Math.exp(-value)));
	}
	
	public float Threshold(float input)
	{
		if(input > 0.0f)
			return 1.0f;
		else return 0.0f;
	}
}

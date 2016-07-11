package Perceptron;

public class Neuron 
{
	private float output;
	private float weights[];
	private float bias;
	private float biasDifference;
	private float delta;
	private float weightDifference[];
	ActivationFn fn;
	
	public Neuron(int weightCount, int activationFn)
	{
		bias = 0.0f;
		biasDifference = 0.0f;
		delta = 0.0f;
		
		weightDifference = new float[weightCount];
		weights = new float[weightCount];
		for(int i = 0;i < weightCount;i++)
		{
			weights[i] = 0.0f;
			weightDifference[i] = 0.0f;
		}
		
		fn = new ActivationFn(activationFn);

		
	}
	
	public void initialize()
	{
		bias = (float)(-1 + 2 * Math.random());
		bias = Float.parseFloat(String.format("%.2f", bias));
		
		for(int i = 0;i < weights.length;i++)
		{
			weights[i] = (float)(-1 + 2 * Math.random());
			weights[i] = Float.parseFloat(String.format("%.2f", weights[i]));
		}
	}

	public float compute(float input[])
	{
		float sum = bias;
		for(int i = 0;i < weights.length;i++)
		{
			sum = sum + input[i] * weights[i];
		}
		output = fn.activate(sum);
		return output;
	}
	
	public float getOutput()
	{
		return output;
	}
	
	public float[] getWeights()
	{
		return weights;
	}
	
	public void setWeight(int i, float weight)
	{
		weights[i] = weight;
	}
	
	public void setDelta(float error)
	{
		delta = error * fn.activateDerivative(output);
	}
	
	public float getDelta()
	{
		return delta;
	}
	
	public float getBias()
	{
		return bias;
	}
	
	public void updateWeights(float momentum, float LR, float prevOutput[])
	{
		biasDifference = momentum * biasDifference + LR * delta;
		bias = bias + biasDifference;
		
		for(int i = 0;i < weights.length;i++)
		{
			weightDifference[i] = momentum * weightDifference[i] + LR * delta * prevOutput[i];
			weights[i] = weights[i] + weightDifference[i];
		}
	}
}

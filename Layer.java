package Perceptron;

public class Layer 
{
	private Neuron neurons[];
	private int neuronCount;
	private int weightCount;
	private float error[];
	public float output[];
	
	public Layer(int neuronCount, int weightCount, int activationFn)
	{
		this.neuronCount = neuronCount;
		this.weightCount = weightCount;
		
		neurons = new Neuron[neuronCount];
		for(int i = 0;i < neuronCount;i++)
		{
			neurons[i] = new Neuron(weightCount, activationFn);
		}
	}
	
	public void initialize(int l)
	{
		if(l == 0)
		{
			for(int i = 0;i < neurons.length;i++)
			{
				neurons[i].setWeight(i, 1.0f);
			}
			return;
		}
		for(int i = 0;i < neurons.length;i++)
		{
			neurons[i].initialize();
		}
	}
	
	public float[] compute(float input[])
	{
		output = new float[neurons.length];
		for(int i = 0;i < neurons.length;i++)
		{
			output[i] = neurons[i].compute(input);
		}
		return output;
	}
	
	public int getNeuronCount()
	{
		return neuronCount;
	}
	
	public float[] getOutput()
	{
		return output;
	}
	
	public void setDelta(float error[])
	{
		for(int i = 0;i < neurons.length;i++)
		{
			neurons[i].setDelta(error[i]);
		}
	}
	
	public void calculateError()
	{
		error = new float[weightCount];
		for(int i = 0;i < weightCount;i++)
		{
			for(int j = 0;j < neurons.length;j++)
			{
				error[i] = neurons[j].getDelta() * neurons[j].getWeights()[i];
			}
		}
	}
	
	public float[] getError()
	{
		return error;
	}
	
	public void updateWeights(float momentum, float LR, float output[])
	{
		for(int i = 0;i < neurons.length;i++)
		{
			neurons[i].updateWeights(momentum, LR, output);
		}
	}
}

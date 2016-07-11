package Perceptron;

public class Network 
{
	private Layer layers[];
	private float momentum;
	private float LR;
	
	public Network(int neuronCount[], int activationFnList[], float momentum, float LR)
	{
		this.momentum = momentum;
		this.LR = LR;
		layers = new Layer[neuronCount.length];
		
		int prevNeuronCount = neuronCount[0];
		for(int i = 0;i < neuronCount.length;i++)
		{
			layers[i] = new Layer(neuronCount[i], prevNeuronCount, activationFnList[i]);
			layers[i].initialize(i);
			prevNeuronCount = neuronCount[i];
		}
	}
	
	public float[] feedForward(float input[])
	{
		float output[] = input;
		for(int i = 0;i < layers.length;i++)
		{
			output = layers[i].compute(input);
			input = output;
		}
		return output;
	}
	
	public void backpropagate(float actualOutput[], float expectedOutput[])
	{
		Layer outputLayer = layers[layers.length - 1];
		
		float error[] = new float[outputLayer.getNeuronCount()];
		
		for(int i = 0;i < error.length;i++)
		{
			error[i] = expectedOutput[i] - actualOutput[i];
		}
		
		for(int i = layers.length - 1;i > 0;i--)
		{
			layers[i].setDelta(error);
			layers[i].calculateError();
			error = layers[i].getError();
			
			layers[i].updateWeights(momentum, LR, layers[i - 1].getOutput());
		}
	}
	
	public void train(float input[], float expectedOutput[])
	{
		float actualOutput[] = test(input);
		backpropagate(actualOutput, expectedOutput);
	}
	
	public float[] test(float input[])
	{
		return feedForward(input);
	}
}

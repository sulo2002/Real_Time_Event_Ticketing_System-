// src/services/ticketService.ts

const API_BASE_URL = 'http://localhost:8080';

export interface ConfigData {
  totalTickets: number;
  retrievalRate: number;
  releaseRate: number;
  maxCap: number;
  noVendors: number;
  noCustomers: number;
}

export const ticketService = {
  // Save configuration
  async saveConfig(config: ConfigData): Promise<void> {
    try {
      const response = await fetch(`${API_BASE_URL}/configure/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(config),
      });
      if (!response.ok) throw new Error('Failed to save configuration');
    } catch (error) {
      console.error('Error saving config:', error);
      throw error;
    }
  },

  // Start the system
  async startSystem(): Promise<void> {
    try {
      const response = await fetch(`${API_BASE_URL}/ticketing/start`, {
        method: 'POST',
      });
      if (!response.ok) throw new Error('Failed to start system');
    } catch (error) {
      console.error('Error starting system:', error);
      throw error;
    }
  },

  // Stop the system
  async stopSystem(): Promise<void> {
    try {
      const response = await fetch(`${API_BASE_URL}/ticketing/stop`, {
        method: 'POST',
      });
      if (!response.ok) throw new Error('Failed to stop system');
    } catch (error) {
      console.error('Error stopping system:', error);
      throw error;
    }
  },

  // Get total tickets
  async getTotalTickets(): Promise<number> {
    try {
      const response = await fetch(`${API_BASE_URL}/configure/totTickets`);
      if (!response.ok) throw new Error('Failed to get total tickets');
      return await response.json();
    } catch (error) {
      console.error('Error getting total tickets:', error);
      throw error;
    }
  }
};
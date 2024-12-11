import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Play, Square } from 'lucide-react';
import { useState } from 'react';


import './App.css' 

const App = () => {
  const [totalTickets, setTotalTickets] = useState('');
  const [releaseRate, setReleaseRate] = useState('');
  const [retrievalRate, setRetrievalRate] = useState('');
  const [maxCap, setMaxCap] = useState('');
  const [noVendors, setNoVendors] = useState('');
  const [noCustomers, setNoCustomers] = useState('');
  const [systemStatus, setSystemStatus] = useState('STOPPED');

  const formHandler = async (e) => {
    e.preventDefault();
    const formdata = { totalTickets, releaseRate, retrievalRate, maxCap, noVendors, noCustomers };

    try {
      const response = await fetch("http://localhost:8080/form/insert", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formdata)
      });

      if (response.ok) {
        alert("Form data saved successfully");
     
      } else {
        const errorData = await response.json();
        alert(`Error: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error saving form data:", error);
      alert("An error occurred while saving form data.");
    }
  };

  
  const handleStart = async () => {
    try {
      const response = await fetch('http://localhost:8080/ticketing/start', {
        method: 'POST'
      });

      if (response.ok) {
        setSystemStatus('RUNNING');
        alert("System started");
      } else {
        const errorData = await response.json();
        alert(`Error: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error starting system:", error);
      alert("An error occurred while starting the system.");
    }
  };

  const handleStop = async () => {
    try {
      const response = await fetch('http://localhost:8080/ticketing/stop', {
        method: 'POST'
      });

      if (response.ok) {
        setSystemStatus('STOPPED');
        alert("System stopped");
      } else {
        const errorData = await response.json();
        alert(`Error: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Error stopping system:", error); 
      alert("An error occurred while stopping the system.");
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 p-6">
      <div className="max-w-7xl mx-auto space-y-6">
        {/* Header with Controls */}
        <Card className="border-2">
          <CardHeader className="pb-2">
            <div className="flex justify-between items-center">
              <CardTitle className="text-2xl font-bold">
                CONFIGURE THE PARAMETERS
              </CardTitle>
              <div className="flex gap-2">
                <Button className="bg-green-600 hover:bg-green-700" onClick={handleStart}>
                  <Play className="h-4 w-4 mr-2" />
                  Start System
                </Button>
                <Button variant="destructive" onClick={handleStop}>
                  <Square className="h-4 w-4 mr-2" />
                  Stop System
                </Button>
              </div>
            </div>
          </CardHeader>
        </Card>

        <div className="grid grid-cols-12 gap-6">
          {/* Configuration Form */}
          <Card className="col-span-12 md:col-span-5">
            <CardHeader>
              <CardTitle>System Configuration</CardTitle>
            </CardHeader>
            <CardContent className="space-y-4">
              <form onSubmit={formHandler}>
                <div className="grid gap-4">
                  <div>
                    <Label>Total Tickets</Label>
                    <Input
                      type="number"
                      value={totalTickets}
                      onChange={(e) => setTotalTickets(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <Label>Release Rate (per second)</Label>
                    <Input
                      type="number"
                      value={releaseRate}
                      onChange={(e) => setReleaseRate(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <Label>Retrieval Rate (per second)</Label>
                    <Input
                      type="number"
                      value={retrievalRate}
                      onChange={(e) => setRetrievalRate(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <Label>Max Capacity</Label>
                    <Input
                      type="number"
                      value={maxCap}
                      onChange={(e) => setMaxCap(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <Label>Number of Vendors</Label>
                    <Input
                      type="number"
                      value={noVendors}
                      onChange={(e) => setNoVendors(e.target.value)}
                      required
                    />
                  </div>
                  <div>
                    <Label>Number of Customers</Label>
                    <Input
                      type="number"
                      value={noCustomers}
                      onChange={(e) => setNoCustomers(e.target.value)}
                      required
                    />
                  </div>
                  <Button type="submit" variant="default">
                    Save
                  </Button>
                </div>
              </form>
            </CardContent>
          </Card>

          {/* System Status and Logs */}
          <div className="col-span-12 md:col-span-7 space-y-6">
            {/* Current Status */}
            <Card>
              <CardHeader>
                <CardTitle>Current Status</CardTitle>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <div className="flex justify-between items-center p-2 bg-slate-100 rounded">
                      <span>System Status</span>
                      <span className={`font-medium ${systemStatus === 'RUNNING' ? 'text-green-600' : 'text-red-600'}`}>
                        {systemStatus}
                      </span>
                    </div>
                   
                  </div>
                </div>
              </CardContent>
            </Card>

            {/* Activity Log */}
            <Card>
              <CardHeader>
                <CardTitle>Activity Log</CardTitle>
              </CardHeader>
              <CardContent>
                <ScrollArea className="h-[400px] w-full rounded-md border">
                  <div className="p-4 space-y-2">
                    <p className="text-slate-500 text-center">No activity to display</p>
                  </div>
                </ScrollArea>
              </CardContent>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
};

export default App;

import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Play, Square } from 'lucide-react';

const App = () => {
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
                <Button className="bg-green-600 hover:bg-green-700">
                  <Play className="h-4 w-4 mr-2" />
                  Start System
                </Button>
                <Button variant="destructive">
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
              <div className="grid gap-4">
                <div>
                  <Label>Total Tickets</Label>
                  <Input type="number" />
                </div>
                <div>
                  <Label>Release Rate (per second)</Label>
                  <Input type="number" />
                </div>
                <div>
                  <Label>Retrieval Rate (per second)</Label>
                  <Input type="number" />
                </div>
                <div>
                  <Label>Max Capacity</Label>
                  <Input type="number" />
                </div>
                <div>
                  <Label>Number of Vendors</Label>
                  <Input type="number" />
                </div>
                <div>
                  <Label>Number of Customers</Label>
                  <Input type="number" />
                </div>
              </div>
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
                      <span className="text-red-600 font-medium">STOPPED</span>
                    </div>
                    <div className="flex justify-between items-center p-2 bg-slate-100 rounded">
                      <span>Active Vendors</span>
                      <span className="font-medium">0</span>
                    </div>
                  </div>
                  <div className="space-y-2">
                    <div className="flex justify-between items-center p-2 bg-slate-100 rounded">
                      <span>Tickets Available</span>
                      <span className="font-medium">0</span>
                    </div>
                    <div className="flex justify-between items-center p-2 bg-slate-100 rounded">
                      <span>Active Customers</span>
                      <span className="font-medium">0</span>
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
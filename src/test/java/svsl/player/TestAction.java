package svsl.player;

import org.junit.jupiter.api.Test;
import svsl.machine.MachineState;
import svsl.machine.MachineType;
import svsl.registry.Elements;
import svsl.world.StardewValley;

public class TestAction {

    @Test
    public void testBrew() {
        StardewValley sv = StardewValley.INSTANCE;
        Player player = sv.player;
        player.inv.store(Elements.STRAWBERRY, 999);
        var barrels = player.world().getMachine(MachineType.Barrel);
        while (sv.date.getYear() == 1) {
            if (player.inv.has(Elements.STRAWBERRY) && barrels.countMachine(MachineState.Standby) == 0) {
                player.world().createMachine(MachineType.Barrel, 2);
            }
            if (barrels.countMachine(MachineState.Finished) > 0) {
                barrels.collect().forEach(player.inv::store);
            }
            while (barrels.countMachine(MachineState.Standby) > 0 && player.inv.has(Elements.STRAWBERRY)) {
                player.brew(Elements.STRAWBERRY);
            }
            System.out.printf("%s \t %s \t 工作中:%d 空闲:%d\n", sv.date, player.inv, barrels.countMachine(MachineState.Working), barrels.countMachine(MachineState.Standby));
            sv.update();
        }
    }
}

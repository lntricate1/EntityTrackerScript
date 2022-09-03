global_singleEntities = ['area_effect_cloud', 'armor_stand', 'arrow', 'bat', 'bee', 'blaze', 'boat', 'cat', 'cave_spider', 'chest_minecart', 'chicken', 'cod', 'command_block_minecart', 'cow', 'creeper', 'dolphin', 'donkey', 'dragon_fireball', 'drowned', 'egg', 'elder_guardian', 'end_crystal', 'ender_dragon', 'ender_pearl', 'enderman', 'endermite', 'evoker', 'evoker_fangs', 'experience_bottle', 'experience_orb', 'eye_of_ender', 'falling_block', 'fireball', 'firework_rocket', 'fishing_bobber', 'fox', 'furnace_minecart', 'ghast', 'guardian', 'hoglin', 'hopper_minecart', 'horse', 'husk', 'illusioner', 'iron_golem', 'item', 'item_frame', 'leash_knot', 'lightning_bolt', 'llama', 'llama_spit', 'magma_cube', 'minecart', 'mooshroom', 'mule', 'ocelot', 'painting', 'panda', 'parrot', 'phantom', 'pig', 'piglin', 'piglin_brute', 'pillager', 'player', 'polar_bear', 'potion', 'pufferfish', 'rabbit', 'ravager', 'salmon', 'sheep', 'shulker', 'shulker_bullet', 'silverfish', 'skeleton', 'skeleton_horse', 'slime', 'small_fireball', 'snow_golem', 'snowball', 'spawner_minecart', 'spectral_arrow', 'spider', 'squid', 'stray', 'strider', 'tnt', 'tnt_minecart', 'trader_llama', 'trident', 'tropical_fish', 'turtle', 'vex', 'villager', 'vindicator', 'wandering_trader', 'witch', 'wither', 'wither_skeleton', 'wither_skull', 'wolf', 'zoglin', 'zombie', 'zombie_horse', 'zombie_villager', 'zombified_piglin'];
e1 = ['#arrows', '#beehive_inhabitors', '#impact_projectiles', '#raiders', '#skeletons'];
global_entities = [];
for(e1, global_entities += '!' + _);
for(global_singleEntities, global_entities += '!' + _);
for(e1, global_entities += _);
for(global_singleEntities, global_entities += _);

__config() ->
{
  'commands' ->
  {
    '' -> _() -> print('Entity Tracker app by lntricate\; /entitytracker help for help'),
    'help' -> _() -> help(player()),
    'clear' -> _() -> clear(player(), 'all'),
    'clear <index>' -> _(index) -> clear(player(), index),
    'list' -> _() -> list(player()),

    'points <entities>' -> _(entities) -> points(entities, 100, 100, 100),
    'points <entities> <duration>' -> _(entities, duration) -> points(entities, duration, 100, 100),
    'points <entities> <duration> <limit>' -> _(entities, duration, limit) -> points(entities, duration, limit, 100),
    'points <entities> <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> points(entities, duration, limit, radius),

    'boxes <entities>' -> _(entities) -> boxes(entities, 100, 100, 100, 'auto', 'auto', 'auto'),
    'boxes <entities> <duration>' -> _(entities, duration) -> boxes(entities, duration, 100, 100, 'auto', 'auto', 'auto'),
    'boxes <entities> <duration> <limit>' -> _(entities, duration, limit) -> boxes(entities, duration, limit, 100, 'auto', 'auto', 'auto'),
    'boxes <entities> <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> boxes(entities, duration, limit, radius, 'auto', 'auto', 'auto'),
    'boxes <entities> <duration> <limit> <radius> eyes' -> _(entities, duration, limit, radius) -> boxes(entities, duration, limit, radius, 'eyes', 'auto', 'auto'),
    'boxes <entities> <duration> <limit> <radius> <sizeX> <sizeY>' -> _(entities, duration, limit, radius, sizeX, sizeY) -> boxes(entities, duration, limit, radius, 'custom', sizeX, sizeY),

    'ticks <entities>' -> _(entities) -> ticks(entities, 100, 100, 100, 'auto', 'auto', 'auto'),
    'ticks <entities> <duration>' -> _(entities, duration) -> ticks(entities, duration, 100, 100, 'auto', 'auto', 'auto'),
    'ticks <entities> <duration> <limit>' -> _(entities, duration, limit) -> ticks(entities, duration, limit, 100, 'auto', 'auto', 'auto'),
    'ticks <entities> <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> ticks(entities, duration, limit, radius, 'auto', 'auto', 'auto'),
    'ticks <entities> <duration> <limit> <radius> eyes' -> _(entities, duration, limit, radius) -> ticks(entities, duration, limit, radius, 'eyes', 'auto', 'auto'),
    'ticks <entities> <duration> <limit> <radius> <sizeX> <sizeY>' -> _(entities, duration, limit, radius, sizeX, sizeY) -> ticks(entities, duration, limit, radius, 'custom', sizeX, sizeY),

    'deaths <entities>' -> _(entities) -> deaths(entities, 100, 100, 100, 'auto', 'auto', 'auto'),
    'deaths <entities> <duration>' -> _(entities, duration) -> deaths(entities, duration, 100, 100, 'auto', 'auto', 'auto'),
    'deaths <entities> <duration> <limit>' -> _(entities, duration, limit) -> deaths(entities, duration, limit, 100, 'auto', 'auto', 'auto'),
    'deaths <entities> <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> deaths(entities, duration, limit, radius, 'auto', 'auto', 'auto'),
    'deaths <entities> <duration> <limit> <radius> <sizeX> <sizeY>' -> _(entities, duration, limit, radius, sizeX, sizeY) -> deaths(entities, duration, limit, radius, 'custom', sizeX, sizeY),

    'lines <entities>' -> _(entities) -> lines(entities, 'auto', 100, 100, 100),
    'lines <entities> <duration>' -> _(entities, duration) -> lines(entities, 'auto', duration, 100, 100),
    'lines <entities> <duration> <limit>' -> _(entities, duration, limit) -> lines(entities, 'auto', duration, limit, 100),
    'lines <entities> <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> lines(entities, 'auto', duration, limit, radius),

    'lines <entities> straight' -> _(entities) -> lines(entities, 'straight', 100, 100, 100),
    'lines <entities> straight <duration>' -> _(entities, duration) -> lines(entities, 'straight', duration, 100, 100),
    'lines <entities> straight <duration> <limit>' -> _(entities, duration, limit) -> lines(entities, 'straight', duration, limit, 100),
    'lines <entities> straight <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> lines(entities, 'straight', duration, limit, radius),

    'lines <entities> motion' -> _(entities) -> lines(entities, 'motion', 100, 100, 100),
    'lines <entities> motion <duration>' -> _(entities, duration) -> lines(entities, 'motion', duration, 100, 100),
    'lines <entities> motion <duration> <limit>' -> _(entities, duration, limit) -> lines(entities, 'motion', duration, limit, 100),
    'lines <entities> motion <duration> <limit> <radius>' -> _(entities, duration, limit, radius) -> lines(entities, 'motion', duration, limit, radius),

    'labels <label_type> <precision> <duration> <limit> <radius> <entities>' -> 'labels',

    'labels lifetime <duration> <limit> <radius> <entities>' -> _(duration, limit, radius, entities) -> labels('lifetime', null, duration, limit, radius, entities),

    'labels fuse <duration> <limit> <radius>' -> _(duration, limit, radius) -> labels('fuse', null, duration, limit, radius, 'tnt'),

    'explosions' -> _() -> explosions('points', 100, 100, 100, 'auto', 'auto', ''),
    'explosions <duration>' -> _(duration) -> explosions('points', duration, 100, 100, 'auto', 'auto', ''),
    'explosions <duration> <limit>' -> _(duration, limit) -> explosions('points', duration, limit, 100, 'auto', 'auto', ''),
    'explosions <duration> <limit> <radius>' -> _(duration, limit, radius) -> explosions('points', duration, limit, radius, 'auto', 'auto', ''),

    'explosions <duration> <limit> <radius> <sizeX> <sizeY>' -> _(duration, limit, radius, sizeX, sizeY) -> explosions('boxes', duration, limit, radius, sizeX, sizeY, ''),

    'explosions rays_entities' -> _() -> explosions('rays_entities', 100, 100, 100, 'auto', 'auto', '@e'),
    'explosions rays_entities <duration>' -> _(duration) -> explosions('rays_entities', duration, 100, 100, 'auto', 'auto', '@e'),
    'explosions rays_entities <duration> <limit>' -> _(duration, limit) -> explosions('rays_entities', duration, limit, 100, 'auto', 'auto', '@e'),
    'explosions rays_entities <duration> <limit> <radius>' -> _(duration, limit, radius) -> explosions('rays_entities', duration, limit, radius, 'auto', 'auto', '@e'),
    'explosions rays_entities <duration> <limit> <radius> <entities>' -> _(duration, limit, radius, entities) -> explosions('rays_entities', duration, limit, radius, 'auto', 'auto', entities),

    'explosions rays_blocks' -> _() -> explosions('rays_blocks', 100, 100, 100, 'auto', 'auto', ''),
    'explosions rays_blocks <duration>' -> _(duration) -> explosions('rays_blocks', duration, 100, 100, 'auto', 'auto', ''),
    'explosions rays_blocks <duration> <limit>' -> _(duration, limit) -> explosions('rays_blocks', duration, limit, 100, 'auto', 'auto', ''),
    'explosions rays_blocks <duration> <limit> <radius>' -> _(duration, limit, radius) -> explosions('rays_blocks', duration, limit, radius, 'auto', 'auto', ''),
    'explosions rays_blocks <duration> <limit> <radius> <entities>' -> _(duration, limit, radius, entities) -> explosions('rays_blocks', duration, limit, radius, 'auto', 'auto', ''),

    'explosions affected_entities' -> _() -> explosions('affected_entities', 100, 100, 100, 'auto', 'auto', '@e'),
    'explosions affected_entities <duration>' -> _(duration) -> explosions('affected_entities', duration, 100, 100, 'auto', 'auto', '@e'),
    'explosions affected_entities <duration> <limit>' -> _(duration, limit) -> explosions('affected_entities', duration, limit, 100, 'auto', 'auto', '@e'),
    'explosions affected_entities <duration> <limit> <radius>' -> _(duration, limit, radius) -> explosions('affected_entities', duration, limit, radius, 'auto', 'auto', '@e'),
    'explosions affected_entities <duration> <limit> <radius> <entities>' -> _(duration, limit, radius, entities) -> explosions('affected_entities', duration, limit, radius, 'auto', 'auto', entities)
  },
  'arguments' ->
  {
    'index' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> []},
    'label_type' -> {'type' -> 'string', 'options' -> ['position', 'motion', 'lifetime', 'position_bitstring']},
    'explosion_type' -> {'type' -> 'string', 'options' -> ['rays', 'applied_velocity']},
    'duration' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100, 200, 500]},
    'limit' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100, 99999]},
    'radius' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100]},
    'sizeX' -> {'type' -> 'float', 'suggest' -> [0.98, 0.25]},
    'sizeY' -> {'type' -> 'float', 'suggest' -> [0.98, 0.25]},
    'entities' -> {'type' -> 'string', 'options' -> global_singleEntities},
    'precision' -> {'type' -> 'term', 'min' -> 0, 'suggest' -> [3, 'max']}
  },
  'scope' -> 'global'
};

global_settings = {};
global_count = {};

isTickFrozen() ->
(
  return(loop(2, run('tick freeze')):1:0~'frozen')
);

checkTickTime() ->
(
  if(time() - global_time > 5000,
    if(isTickFrozen,
      global_time = time();
      return()
    );
    print(player('all'), format('br Entity tracker: Max tick time reached. Resume with /script resume'));
    for(entity_selector('@e'), entity_event(_, 'on_move', _(e,m,p1,p2) -> ''));
    run('script stop')
  )
);

updateUsedEntities(player, e, type) ->
(
  if(global_usedEntitiesForTick == {},
    global_usedEntitiesForTick = {player -> {e~'uuid' -> {type -> 0}}},
    if(global_usedEntitiesForTick:player:(e~'uuid') == null,
      global_usedEntitiesForTick:player = global_usedEntitiesForTick:player + {e~'uuid' -> {type -> 0}},
      global_usedEntitiesForTick:player:(e~'uuid') = global_usedEntitiesForTick:player:(e~'uuid') + {type -> 0}
    )
  );
);

bitstring(n) ->
(
  n = double_to_long_bits(n);
  ret='';
  c_for(i = 63, i >= 0, i += -1,
    p = 2^i;
    if(n >= p,
      ret += 1;
      n = bitwise_xor(n, p),
      ret += 0
    )
  );
  ret
);

__on_tick() ->
(
  global_usedEntitiesForTick = {};
  global_count = {};
  global_time = time();
  global_optimizedTnt = system_info('world_carpet_rules'):'optimizedTNT' == 'true';
  for(pairs(global_settings),
    if(_:0 == 'explosions', continue());
    entity = _:0;
    data = _:1;
    for(entity_selector('@e[type=' + entity + ']'),
      createOnRemovedEvents(_, data);
      createOnTickEvents(_, data);
      createOnMoveEvents(_, data);
    )
  )
);

__on_explosion_outcome(pos, power, source, causer, mode, fire, blocks, entities) ->
(
  checkTickTime();
  for(global_settings:'explosions',
    playerString = _:0;
    player = player(playerString);
    if(player == null, continue());
    d = pos - player~'pos';
    if((d:0)^2 + (d:1)^2 + (d:2)^2 > (_:4)^2, continue());
    type = _:1;
    limit = _:3;
    if(global_count:playerString:type >= limit, continue());
    duration = _:2;
    arguments = _:5;

    if(global_count:playerString == null, global_count = global_count + {playerString -> {type -> 1}},
      if(global_count:playerString:type == null,
        global_count:playerString = global_count:playerString + {type -> 1},
        global_count:playerString:type += 1
      )
    );

    if(type == 'points',
      drawPoint(player, 0xFF0000FF, duration, pos);
      continue()
    );

    if(type == 'boxes',
      drawBox(player, 0xFF0000FF, 0xFF000044, duration, pos, arguments:0, arguments:1);
      continue()
    );

    if(type == 'rays_blocks',
      drawBlockRays(player, duration, pos, power);
      continue()
    );

    for(entities,
      if(arguments:2 == '@e' && _~'type' != 'player' || _~'type' == arguments:2,
        if(type == 'affected_entities', drawAppliedVelocity(player, duration, pos, _, power, 10); break());
        drawRays(player, duration, pos, _)
      )
    )
  )
);

createLoadHandlers() ->
(
  for(pairs(global_settings),
    if(_:0 == 'explosions', continue());
    entity = _:0;
    data = _:1;
    entity_load_handler(entity, _(e, new, outer(data)) -> for(data,
      if(_:1 == 'points' || _:1 == 'boxes' || _:1 == 'ticks' || _:1 == 'motion_lines',
        p = e~'pos';
        m = e~'motion';
        player = player(_:0);
        if(player == null, continue());
        d = p - player~'pos';
        if((d:0)^2 + (d:1)^2 + (d:2)^2 > (_:4)^2, continue());
        type = _:1;
        limit = _:3;
        if(global_count:playerString:type >= limit, continue());
        duration = _:2;
        arguments = _:5;

        if(type == 'motion_lines', drawMotion(player, 0xFF0000FF, duration, m, p); continue());

        if(type == 'points', drawPoint(player, 0xFFFFFFFF, duration, p); continue());

        if(arguments:0 == 'custom', sizex = arguments:1; sizey = arguments:2, sizex = e~'width'; sizey = e~'height');
        if(arguments:0 == 'eyes', drawEyeHeight(player, 0x0000FFFF, duration, p, sizex, e));
        if(type == 'ticks',
          drawBox(player, 0xFFFFFFFF, 0xFF00FF88, duration, p, sizex, sizey),
          drawBox(player, 0xFFFFFFFF, 0x00000000, duration, p, sizex, sizey)
        )
      ,
        createOnRemovedEvents(e, data);
        createOnMoveEvents(e, data)
      )
    ))
  )
);

createOnRemovedEvents(e, data) ->
(
  entity_event(e, 'on_removed', _(e, outer(data)) -> for(data,
    p = e~'pos';
    m = e~'motion';
    player = player(_:0);
    if(player == null, continue());
    d = p - player~'pos';
    if((d:0)^2 + (d:1)^2 + (d:2)^2 > (_:4)^2, continue());
    type = _:1;
    limit = _:3;
    if(global_count:playerString:type >= limit, continue());
    duration = _:2;
    arguments = _:5;

    if(type == 'deaths',
      if(arguments:0 == 'custom', sizex = arguments:1; sizey = arguments:2, sizex = e~'width'; sizey = e~'height');
      drawBox(player, 0xFF0000FF, 0x00000000, duration, p, sizex, sizey);
      drawMotion(player, 0xFF0000FF, duration, m, p)
    )
  ))
);

createOnTickEvents(e, data) ->
(
  entity_event(e, 'on_tick', _(e, outer(data)) -> for(data,
    p = e~'pos';
    m = e~'motion';
    player = player(_:0);
    if(player == null, continue());
    d = p - player~'pos';
    if((d:0)^2 + (d:1)^2 + (d:2)^2 > (_:4)^2, continue());
    type = _:1;
    limit = _:3;
    if(global_count:playerString:type >= limit, continue());
    duration = _:2;
    arguments = _:5;

    if(type == 'ticks',
      if(arguments:0 == 'custom', sizex = arguments:1; sizey = arguments:2, sizex = e~'width'; sizey = e~'height');
      if(arguments:0 == 'eyes', drawEyeHeight(player, 0x0000FFFF, duration, p, sizex, e));
      drawBox(player, 0xFFFFFFFF, 0xFF00FF88, duration, p, sizex, sizey);
      continue()
    );

    if(type == 'motion_lines' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawMotion(player, 0xFF0000FF, duration, m, p); updateUsedEntities(player, e, type); continue());

    if(type == 'position_label', drawLabel(player, 0x0000FFFF, 'bottom', duration,
        p + [0, e~'height', 0], if(arguments:0 != 'max', roundTriple(p, arguments:0), p)); continue());

    if(type == 'position_bitstring_label',
      draw_shape('label', duration, 'text', bitstring(p:0), 'height', 3, 'pos', p+[0,e~'height',0], 'player', player, 'color', 0x0000FFFF);
      draw_shape('label', duration, 'text', bitstring(p:1), 'height', 2, 'pos', p+[0,e~'height',0], 'player', player, 'color', 0x0000FFFF);
      draw_shape('label', duration, 'text', bitstring(p:2), 'height', 1, 'pos', p+[0,e~'height',0], 'player', player, 'color', 0x0000FFFF); continue());

    if(type == 'motion_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0xFF0000FF, 'top', duration,
        p, if(arguments:0 != 'max', roundTriple(m, arguments:0), m)); updateUsedEntities(player, e, type); continue());

    if(type == 'lifetime_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0x00FF00FF, 'center', duration,
        p + [0, e~'height' / 2, 0], e~'age'); updateUsedEntities(player, e, type); continue());

    if(type == 'fuse_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0x00FF00FF, 'center', duration,
        p + [0, e~'height' / 2, 0], query(e, 'nbt', 'Fuse')); updateUsedEntities(player, e, type))
  ))
);

createOnMoveEvents(e, data) ->
(
  entity_event(e, 'on_move', _(e, m, p1, p2, outer(data)) -> for(data,
    playerString = _:0;
    player = player(playerString);
    if(player == null, continue());
    d = p1 - player~'pos';
    if((d:0)^2 + (d:1)^2 + (d:2)^2 > (_:4)^2, continue());
    type = _:1;
    limit = _:3;
    if(global_count:playerString:type >= limit, continue());
    duration = _:2;
    arguments = _:5;

    if(global_count:playerString == null, global_count = global_count + {playerString -> {type -> 1}},
      if(global_count:playerString:type == null,
        global_count:playerString = global_count:playerString + {type -> 1},
        global_count:playerString:type += 1
      )
    );

    if(type == 'points', drawPoint(player, 0xFFFFFFFF, duration, p2); continue());

    if(type == 'boxes',
      if(arguments:0 == 'custom', sizex = arguments:1; sizey = arguments:2, sizex = e~'width'; sizey = e~'height');
      if(arguments:0 == 'eyes', drawEyeHeight(player, 0x0000FFFF, duration, p2, sizex, e));
      drawBox(player, 0xFFFFFFFF, 0x00000000, duration, p2, sizex, sizey);
      continue()
    );

    if(type == 'straight_lines', drawStraightLine(player, 0x0000FFFF, duration, p1, p2); continue());

    if(type == 'axis_lines', drawAxisLines(player, 0x0000FFFF, duration, m, p1, p2); continue())
  ))
);

help(player) ->
(
    print(player, '');
    print(player, format('b#F5ABB8 Entity Tracker Help'));

    print(player, format('#5BCFFA /entitytracker help'));
    print(player, format('w Shows this menu.'));
    print('');

    print(player, format('#5BCFFA /entitytracker clear <index>'));
    print(player, format('w Clears the tracker at <index>, if empty clears all trackers.'));
    print('');

    print(player, format('#5BCFFA /entitytracker list'));
    print(player, format('w Shows all active trackers.'));
    print('');

    print(player, format('#5BCFFA /entitytracker lines <mode> <duration> <entity limit> <radius> <entity selector>'));
    print(player, format('w Draws lines following entity movement.
    ', '#F5ABB8 \'straight\'', 'w  mode draws straight lines.
    ', '#F5ABB8 \'accurate\'', 'w  mode draws the real collision checks (not used in projectiles).
    And ', '#F5ABB8 \'motion\'', 'w  draws the motion vector.'));
    print('');

    print(player, format('#5BCFFA /entitytracker labels <mode> <precision> <duration> <entity limit> <radius> <entity selector>'));
    print(player, format('w Draws labels following entity movement.
    ', '#F5ABB8 \'positions\'', 'w  mode says the position vector.
    ', '#F5ABB8 \'motion\'', 'w  mode says the velocity vector.
    And ', '#F5ABB8 \'lifetime\'', 'w  mode says the age of the entity.'));
    print('');

    print(player, format('#5BCFFA /entitytracker points <sizex> <sizey> <eyes?> <duration> <entity limit> <radius> <entity selector>'));
    print(player, format('w Draws a box at each position the entity exists, with size <sizex> * <sizey>. The box\'s feet will be centered at the entity\'s feet.'));
    print('');

    print(player, format('#5BCFFA /entitytracker explosions <mode> <duration> <limit> <radius>'));
    print(player, format('w Tracks explosions.
    ', '#F5ABB8 \'points\'', 'w  mode draws a box at each position there is an explosion, centered at the explosion.
    ', '#F5ABB8 \'rays\'', 'w  mode draws the raycasts used to calculate exposure, but does not omit the ones that don\'t reach.
    And ', '#F5ABB8 \'applied_velocity\'', 'w  mode draws the velocity applied to each affected entity, multiplied by 10. Does not factor in exposure.'));
    print('');
);

clear(player, index) ->
(
  if(index != 'all', remove(player, index),
    print(player, '');
    if(getPlayerSettings(player) == {},
      print(player, format('#F5ABB8 No trackers configured'));
      return()
    );
    amount = 0;
    settings = {};
    for(pairs(global_settings),
      [e, data] = _;
      for(data, if(player(_:0) == player, delete(global_settings:e, _); amount += 1))
    );
    if(amount == 1,
      print(player, format(
      '#F5ABB8 Cleared ',
      '#5BCFFA 1',
      '#F5ABB8  tracker for ',
      '#5BCFFA ' + player))
    ,
      print(player, format(
      '#F5ABB8 Cleared ',
      '#5BCFFA ' + amount,
      '#F5ABB8  trackers for ',
      '#5BCFFA ' + player))
    )
  )
);

list(player) ->
(
  settings = getPlayerSettings(player);
  if(settings == {},
    print(player, '');
    print(player, format('#F5ABB8 No trackers configured'));
    return()
  );
  print(player, '');
  print(player, format('#F5ABB8 List of trackers for ',
  '#5BCFFA ' + player,
  '#F5ABB8  (remove any entry using ',
  '#5BCFFA /entitytracker clear <index>', '#F5ABB8 )'));
  for(pairs(settings),
    [e, data] = _;
    print(player, '');
    print(player, format('#F5ABB8 ' + e + ':'));
    for(pairs(data),
      [index, args] = _;
      type = args:1;
      duration = args:2;
      limit = args:3;
      radius = args:4;
      arguments = args:5;
      if(e != 'explosions',
        if(type == 'points' || type == 'axis_lines' || type == 'straight_lines' || type == 'motion_lines',
          print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA ' + type, 'w , duration: ', '#5BCFFA ' + duration,
          'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

        if(type == 'boxes' || type == 'ticks' || type == 'deaths', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA ' + type, 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius
        , 'w , size: ', '#5BCFFA ' + arguments:1, 'w *', '#5BCFFA ' + arguments:2)));

        if(type == 'position_label', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA position label', 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius
        , 'w , precision: ', '#5BCFFA ' + arguments:0)));

        if(type == 'motion_label', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA motion label', 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius
        , 'w , precision: ', '#5BCFFA ' + arguments:0)));

        if(type == 'lifetime_label', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA lifetime label', 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radiuss
        , 'w , precision: ', '#5BCFFA ' + arguments:0)))

        ,

        if(type == 'points' || type == 'rays_blocks', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA ' + type, 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

        if(type == 'boxes', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA ' + type, 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius
        , 'w , size: ', '#5BCFFA ' + arguments:1, 'w *', '#5BCFFA ' + arguments:2)));

        if(type == 'rays_entities' || type == 'affected_entities', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA ' + type, 'w , duration: ',
        '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)))
      )
    )
  )
);

remove(player, index) ->
(
  playerSettings = getPlayerSettings(player);
  for(pairs(playerSettings),
    [e, data] = _;
    if(data:index != null,
      delete(global_settings:e, data:index);
      print(player, format(
        '#F5ABB8 Removed ' + index + ': ',
        '#5BCFFA ' + data:index:1));
      return()
    )
  );
  print(player, format('#F5ABB8 Index not found'));
);

points(e, duration, limit, radius) ->
(
  addSetting(e, [player()~'name', 'points', duration, limit, radius, [null, 'auto', 'auto']]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA points ',
  '#F5ABB8 for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit,
  '#F5ABB8  and size ', '#5BCFFA ' + size + ' (' + eyes + ')'))
);

boxes(e, duration, limit, radius, type, sizex, sizey) ->
(
  addSetting(e, [player()~'name', 'boxes', duration, limit, radius, [type, sizex, sizey]]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA boxes ',
  '#F5ABB8 for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit,
  '#F5ABB8  and size ', '#5BCFFA ' + size + ' (' + eyes + ')'))
);

ticks(e, duration, limit, radius, type, sizex, sizey) ->
(
  addSetting(e, [player()~'name', 'ticks', duration, limit, radius, [type, sizex, sizey]]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA ticks ',
  '#F5ABB8 for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit,
  '#F5ABB8  and size ', '#5BCFFA ' + size + ' (' + eyes + ')'))
);

deaths(e, duration, limit, radius, type, sizex, sizey) ->
(
  addSetting(e, [player()~'name', 'deaths', duration, limit, radius, [type, sizex, sizey]]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA deaths ',
  '#F5ABB8 for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit,
  '#F5ABB8  and size ', '#5BCFFA ' + size + ' (' + eyes + ')'))
);

lines(e, type, duration, limit, radius) ->
(
  if(type == 'auto',
    if(e == 'ender_pearl' || e == 'snowball' || e == 'egg' || e == 'fireball' || e == 'small_fireball' || e == 'dragon_fireball' || e == 'arrow' || e == 'trident',
      type = 'straight',
      type = 'axis'
    )
  );
  addSetting(e, [player()~'name', type + '_lines', duration, limit, radius]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA ' + type + '_lines',
  '#F5ABB8  for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit))
);

labels(type, precision, duration, limit, radius, e) ->
(
  if(precision != 'max',
    precision = number(precision);
    if(type(precision) != 'number',
    print(player(), 'Invalid size!'); return())
  );
  addSetting(e, [player()~'name', type + '_label', duration, limit, radius, [precision]]);
  print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA ' + type + '_label',
  '#F5ABB8  for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
  '#F5ABB8  with max count ', '#5BCFFA ' + limit, '#F5ABB8  and precision ', '#5BCFFA ' + precision))
);

explosions(type, duration, limit, radius, sizex, sizey, entities) ->
(
  addSetting('explosions', [player()~'name', type, duration, limit, radius, [sizex, sizey, entities]]);
  if(type == 'points' || type == 'blockRays',
    print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA explosions: ' + type,
    '#F5ABB8  in radius ', '#5BCFFA ' + radius,
    '#F5ABB8  with max count ', '#5BCFFA ' + limit)),

    print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA explosions: ' + type,
    '#F5ABB8  in radius ', '#5BCFFA ' + radius,
    '#F5ABB8  with max count ', '#5BCFFA ' + limit,
    '#F5ABB8  for ', '#5BCFFA ' + e)),
  )
);

drawAxisLines(pl, col, duration, m, p1, p2) ->
(
  draw_shape('line', duration, 'color', col, 'player', pl, 'from', p1, 'to', [p1:0, p2:1, p1:2]);
  if(abs(m:0) >= abs(m:2),
    draw_shape('line', duration, 'color', col, 'player', pl, 'from', [p1:0, p2:1, p1:2], 'to', [p2:0, p2:1, p1:2]);
    draw_shape('line', duration, 'color', col, 'player', pl, 'from', [p2:0, p2:1, p1:2], 'to', [p2:0, p2:1, p2:2]),
    draw_shape('line', duration, 'color', col, 'player', pl, 'from', [p1:0, p2:1, p1:2], 'to', [p1:0, p2:1, p2:2]);
    draw_shape('line', duration, 'color', col, 'player', pl, 'from', [p1:0, p2:1, p2:2], 'to', [p2:0, p2:1, p2:2])
  );
);

drawMotion(pl, col, duration, m, p) ->
(
  draw_shape('line', duration, 'color', col, 'player', pl, 'from', p, 'to', p + m)
);

drawStraightLine(pl, col, duration, p1, p2) ->
(
  draw_shape('line', duration, 'color', col, 'player', pl, 'from', p1, 'to', p2)
);

drawPoint(pl, col, duration, pos) ->
(
  draw_shape('box', duration, 'color', col, 'player', pl,
    'from', pos - [0.00625, 0, 0.00625],
    'to', pos + [0.00625, 0.0125, 0.00625]);
);

drawBox(pl, col, col1, duration, pos, sizex, sizey) ->
(
  sizex = sizex / 2;
  draw_shape('box', duration, 'color', col, 'fill', col1, 'player', pl,
  'from', pos - [sizex, 0, sizex],
  'to', pos + [sizex, sizey, sizex]);
);

drawEyeHeight(pl, col, duration, pos, w, e) ->
(
  pos = pos + [0, e~'eye_height', 0];
  w = w/2;
  drawStraightLine(pl, col, duration, pos + [-w, 0, -w], pos + [-w, 0, w]);
  drawStraightLine(pl, col, duration, pos + [-w, 0, w], pos + [w, 0, w]);
  drawStraightLine(pl, col, duration, pos + [w, 0, w], pos + [w, 0, -w]);
  drawStraightLine(pl, col, duration, pos + [w, 0, -w], pos + [-w, 0, -w]);

  drawStraightLine(pl, col, duration, pos + [-w, 0, -w], pos + [w, 0, w]);
  drawStraightLine(pl, col, duration, pos + [w, 0, -w], pos + [-w, 0, w]);
);

drawLabel(pl, col, align, duration, p, text) ->
(
  if(align == 'bottom',
    draw_shape('label', duration, 'color', col, 'height', 1, 'pos', p, 'text', text));
  if(align == 'center',
    draw_shape('label', duration, 'color', col, 'height', -0.5, 'pos', p, 'text', text));
  if(align == 'top',
    draw_shape('label', duration, 'color', col, 'height', -2, 'pos', p, 'text', text));
);

drawRays(player, duration, pos, entity) ->
(
  w = entity~'width';
  h = entity~'height';
  ep = entity~'pos';
  dx = 1 / (w * 2 + 1);
  dy = 1 / (h * 2 + 1);
  offset = (1 - floor(1 / dx) * dx) / 2;
  c_for(x = 0, x <= 1, x += dx,
    c_for(y = 0, y <= 1, y += dy,
      c_for(z = 0, z <= 1, z += dx,
        drawStraightLine(player, 0xFF0000FF, duration, pos, [
          ep:0 - w / 2 + offset + x * w,
          ep:1 + y * h,
          ep:2 - w / 2 + offset + z * w])
      )
    )
  )
);

drawBlockRays(player, duration, pos, power) ->
(
  c_for(x = 0, x <= 15, x += 1,
    xRel = x / 15 * 2 - 1;
    c_for(y = 0, y <= 15, y += 1,
      yRel = y / 15 * 2 - 1;
      l = sqrt(xRel * xRel + yRel * yRel + 1);
      px = xRel / l * power * 1.733333;
      py = yRel / l * power * 1.733333;
      pz = 1 / l * power * 1.733333;
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [pz, px, py]);
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [-pz, px, py]);
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [px, pz, py]);
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [px, -pz, py]);
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [px, py, pz]);
      drawStraightLine(player, 0xFF0000FF, duration, pos, pos + [px, py, -pz])
    )
  )
);

drawAppliedVelocity(player, duration, pos, entity, power, multiplier) ->
(
  ep = entity~'pos';
  h = if(entity~'type' == 'tnt', 0, entity~'eye_height');
  dis = dist(pos, ep);
  dir = ep + [0, h, 0] - pos;
  drawStraightLine(player, 0XFF0000FF, duration, pos, pos + dir * (1 - dis / power / 2) * multiplier);
);

addSetting(e, entry) ->
(
  if(global_settings:e != null,
    global_settings:e = global_settings:e + {entry -> null},
    global_settings = global_settings + {e -> {entry -> null}}
  );
  createLoadHandlers()
);

getPlayerSettings(player) ->
(
  settings = {};
  index = 0;
  for(pairs(global_settings),
    [e, data] = _;
    for(keys(data),
      if(player(_:0) == player, if(settings:e == null,
        settings = settings + {e -> {index -> _}},
        settings:e = settings:e + {index -> _});
        index += 1;
      )
    )
  );
  return(settings);
);

dist(p1, p2) ->
(
  d = p2 - p1;
  return(sqrt(d:0^2 + d:1^2 + d:2^2));
);

roundTriple(triple, log) ->
(
  return([round(triple:0 * 10^log) / 10^log,
  round(triple:1 * 10^log) / 10^log,
  round(triple:2 * 10^log) / 10^log])
)

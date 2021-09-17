__config() ->
{
    'commands' ->
    {
        '' -> _() -> print('Entity Tracker app by lntricate\; /entitytracker help for help'),
        'help' -> _() -> help(player()),
        'clear' -> _() -> clear(player(), 'all'),
        'clear <index>' -> _(index) -> clear(player(), index),
        'list' -> _() -> list(player()),

        'points <sizeX> <sizeY> <eyes> <duration> <limit> <radius> <entities>' -> 'points',

        'lines <line_type> <duration> <limit> <radius> <entities>' -> 'lines',

        'labels <label_type> <precision> <duration> <limit> <radius> <entities>' -> 'labels',

        'labels lifetime <duration> <limit> <radius> <entities>' -> 
            _(duration, limit, radius, entities) -> labels('lifetime', null, duration, limit, radius, entities),

        'labels fuse <duration> <limit> <radius>' ->
            _(duration, limit, radius) -> labels('fuse', null, duration, limit, radius, '@e[type=tnt]'),

        'explosions points <duration> <limit> <radius>' ->
            _(duration, limit, radius) -> explosions('points', duration, limit, radius, null),

        'explosions <explosion_type> <duration> <limit> <radius> <entities>' -> 'explosions',

        //'chat <log_type> <limit> <radius> <entities>' -> 'chat',

        //'chat explosions <limit> <radius>' -> _(limit, radius) -> chat('explosions', limit, radius, 'explosions')
    },
    'arguments' ->
    {
        'index' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> []},
        'line_type' -> {'type' -> 'string', 'options' -> ['accurate', 'straight', 'motion']},
        'label_type' -> {'type' -> 'string', 'options' -> ['position', 'motion', 'lifetime']},
        //'log_type' -> {'type' -> 'string', 'options' -> ['position', 'motion']},
        'explosion_type' -> {'type' -> 'string', 'options' -> ['rays', 'applied_velocity']},
        'duration' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100, 200, 500]},
        'limit' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100, 99999]},
        'radius' -> {'type' -> 'int', 'min' -> 0, 'suggest' -> [100]},
        'sizeX' -> {'type' -> 'term', 'suggest' -> [0.98, 0.25, 0.1, 'auto']},
        'sizeY' -> {'type' -> 'term', 'suggest' -> [0.98, 0.25, 0.1, 'auto']},
        'eyes' -> {'type' -> 'term', 'options' -> ['eyes', 'no_eyes']},
        'entities' -> {'type' -> 'text', 'suggest' -> ['@e[type=tnt]', '@e[type=snowball]', '@e[type=ender_pearl]', '@e[type=!player]', '@e[type=']},
        'precision' -> {'type' -> 'term', 'min' -> 0, 'suggest' -> [3, 'max']},
    },
    'scope' -> 'global'
};

global_settings = {};
global_count = {};
global_loggedExplosions = {};
global_explosionParity = 0;

__on_tick() ->
(
    global_collapsedSettings = collapseSettings();
    global_usedEntitiesForTick = {};
    global_count = {};
    for(pairs(global_collapsedSettings),
        if(_:0 == 'explosions', continue());
        [entity, data] = _;
        entity_event(entity, 'on_move', _(e, m, p1, p2, outer(data)) ->
        (
            for(keys(data),
                playerString = _:0;
                player = player(playerString);
                if(player == null, continue());
                radius = _:4;
                if(dist(p1, player~'pos') > radius && dist(p2, player~'pos') > radius, continue());
                type = _:1;
                limit = _:3;
                if(global_count:playerString:type >= limit, continue());
                duration = _:2;
                arguments = _:5;

                if(global_usedEntitiesForTick == {},
                    global_usedEntitiesForTick = {player -> {e~'uuid' -> {type -> 0}}},
                    if(global_usedEntitiesForTick:player:(e~'uuid') == null,
                        global_usedEntitiesForTick:player = global_usedEntitiesForTick:player + {e~'uuid' -> {type -> 0}},
                        global_usedEntitiesForTick:player:(e~'uuid') = global_usedEntitiesForTick:player:(e~'uuid') + {type -> 0}
                    )
                );

                if(global_count:playerString == null, global_count = global_count + {playerString -> {type -> 1}},
                    if(global_count:playerString:type == null,
                        global_count:playerString = global_count:playerString + {type -> 1},
                        global_count:playerString:type += 1
                    )
                );

                if(type == 'points',
                    if(arguments:0 == 'auto', sizex = e~'width', sizex = arguments:0);
                    if(arguments:1 == 'auto', sizey = e~'height', sizey = arguments:1);
                    drawBox(player, 0xFFFFFFFF, duration, p1 + [0, sizey / 2, 0], sizex, sizey);
                    if(arguments:2 == 'eyes', drawEyeHeight(player, 0x0000FFFF, duration, p1, sizex, e));
                    continue();
                );
                if(type == 'straight_lines', drawStraightLine(player, 0x0000FFFF, duration, p1, p2); continue());
                if(type == 'accurate_lines', drawAxisLines(player, 0x0000FFFF, duration, m, p1, p2); continue());
                if(type == 'motion_lines' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawMotion(player, 0xFF0000FF, duration, m, p1); continue());

                if(type == 'position_label', drawLabel(player, 0x0000FFFF, 'top', duration,
                p1, if(arguments:0 != 'max', roundTriple(p1, arguments:0), p1)); continue());

                if(type == 'motion_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0xFF0000FF, 'bottom', duration,
                p1 + [0, e~'height', 0], if(arguments:0 != 'max', roundTriple(m, arguments:0), m)); continue());

                if(type == 'lifetime_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0x00FF00FF, 'center', duration,
                p1 + [0, e~'height' / 2, 0], e~'age'); continue());

                if(type == 'fuse_label' && global_usedEntitiesForTick:player:(e~'uuid'):type == null, drawLabel(player, 0x00FF00FF, 'center', duration,
                p1 + [0, e~'height' / 2, 0], query(e, 'nbt', 'Fuse')));
            )
        ))
    )
    //for(pairs(global_loggedExplosions),
    //    [player, data] = _;
    //    print(player, format('b#5BCFFA @' + world_time()));
    //    for(pairs(data),
    //        [pos, count] = _;
    //        print(player, format('#5BCFFA  ' + count + 'x ', '#F5ABB8  ' + pos))
    //    )
    //);
    //global_loggedExplosions = {};
);

__on_explosion_outcome(pos, power, source, causer, mode, fire, blocks, entities) ->
(
    if(global_explosionParity == 1,
        for(global_collapsedSettings:'explosions',
            playerString = _:0;
            player = player(playerString);
            if(player == null, continue());
            if(dist(pos, player~'pos') > _:4, continue());
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
                drawBox(player, 0xFF0000FF, duration, pos, 0.1, 0.1);
                continue()
            );
            selectedEntities = entity_selector(arguments:0);
            for(entities,
                if(_ == player, continue());
                e = _;
                shouldRender = false;
                for(selectedEntities, if(_ == e,
                    if(type == 'applied_velocity', drawAppliedVelocity(player, duration, pos, e, power, 10); break());
                    drawRays(player, duration, pos, e);
                    break();
                ))
            );

            //actualPos = split('d', query(source, 'nbt', 'Pos'));
            //actualPos = [split('\\[', actualPos:0):1, split(',', actualPos:1):1, split(',', actualPos:2):1];
            //actualPos = [split('\\[', actualPos:0):1, pos:1, split(',', actualPos:2):1];
            //if(global_loggedExplosions == {},
            //    global_loggedExplosions = {player -> {actualPos -> 0}}
            //);
            //if(global_loggedExplosions:player:actualPos == null,
            //    global_loggedExplosions:player = global_loggedExplosions:player + {actualPos -> 0},
            //    global_loggedExplosions:player:actualPos += 1
            //);
        );
        global_explosionParity = 0
    ,
        global_explosionParity = 1
    )
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
            for(keys(data),
                if(player(_:0) != player, addSetting(e, _), amount += 1)
            )
        );
        global_settings = settings;
        print(player, format(
        '#F5ABB8 Cleared ',
        '#5BCFFA ' + amount,
        '#F5ABB8  trackers for ',
        '#5BCFFA ' + player))
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
                if(type == 'accurate_lines', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA accurate lines', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

                if(type == 'straight_lines', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA straight lines', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

                if(type == 'motion_lines', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA motion lines', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

                if(type == 'points', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA points', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius
                , 'w , size: ', '#5BCFFA ' + arguments:0, 'w * ', '#5BCFFA ' + arguments:1 + ' (' + arguments:2 + ')')));
                
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


                if(type == 'points', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA points', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));

                if(type == 'rays', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA rays', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));
                
                if(type == 'applied_velocity', print(player, format('#F5ABB8 ' + index + ': ', '#5BCFFA applied velocity', 'w , duration: ',
                '#5BCFFA ' + duration, 'w , limit: ', '#5BCFFA ' + limit, 'w , radius: ', '#5BCFFA ' + radius)));
            );
        )
    );
);

remove(player, index) ->
(
    player = player();
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

points(sizex, sizey, eyes, duration, limit, radius, e) ->
(
    if(sizex != 'auto', sizex = number(sizex));
    if(sizey != 'auto', sizey = number(sizey));
    if((sizex != 'auto' && type(sizex) != 'number') ||
    (sizey != 'auto' && type(sizey) != 'number'), print(player(), 'Invalid size!'); return());
    addSetting(e, [player()~'name', 'points', duration, limit, radius, [sizex, sizey, eyes]]);
    print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA points ',
    '#F5ABB8 for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
    '#F5ABB8  with max count ', '#5BCFFA ' + limit,
    '#F5ABB8  and size ', '#5BCFFA ' + size + ' (' + eyes + ')'))
);

lines(type, duration, limit, radius, e) ->
(
    addSetting(e, [player()~'name', type + '_lines', duration, limit, radius]);
    print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA ' + type,
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
    print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA ' + type,
    '#F5ABB8  for ', '#5BCFFA ' + e, '#F5ABB8  in radius ', '#5BCFFA ' + radius,
    '#F5ABB8  with max count ', '#5BCFFA ' + limit, '#F5ABB8  and precision ', '#5BCFFA ' + precision))
);

explosions(type, duration, limit, radius, e) ->
(
    addSetting('explosions', [player()~'name', type, duration, limit, radius, [e]]);
    if(type == 'points',
        print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA explosions: ' + type,
        '#F5ABB8  in radius ', '#5BCFFA ' + radius,
        '#F5ABB8  with max count ', '#5BCFFA ' + limit)),

        print(player(), format('#F5ABB8 Now tracking ', '#5BCFFA explosions: ' + type,
        '#F5ABB8  in radius ', '#5BCFFA ' + radius,
        '#F5ABB8  with max count ', '#5BCFFA ' + limit,
        '#F5ABB8  for ', '#5BCFFA ' + e)),
    )
);

//chat(type, limit, radius, e) ->
//(
//    addSetting(e, [player(), type + '_log', null, limit, radius]);
//);

drawAxisLines(pl, col, duration, m, p1, p2) ->
(
    draw_shape('line', duration, 'color', col, 'from', p1, 'to', [p1:0, p2:1, p1:2]);
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

drawBox(pl, col, duration, pos, sizex, sizey) ->
(
    sizex = sizex / 2;
    sizey = sizey / 2;
    draw_shape('box', duration, 'color', col, 'player', pl,
    'from', pos - [sizex, sizey, sizex],
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
        draw_shape('label', duration, 'color', col, 'pos', p, 'text', text));
    if(align == 'center',
        draw_shape('label', duration, 'color', col, 'height', -0.5, 'pos', p, 'text', text));
    if(align == 'top',
        draw_shape('label', duration, 'color', col, 'height', -1, 'pos', p, 'text', text));
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
                    ep:1 + y * w,
                    ep:2 - w / 2 + offset + z * w])
            )
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

collapseSettings() ->
(
    expandedEntities = {};
    for(pairs(global_settings),
        [key, value] = _;
        if(key != 'explosions', for(entity_selector(key),
            if(expandedEntities:_ != null,
                expandedEntities:_ = value + expandedEntities:_,
                expandedEntities = expandedEntities + {_ -> value})
        ),
            if(expandedEntities:_ != null,
                expandedEntities:_ = value + expandedEntities:_,
                expandedEntities = expandedEntities + {key -> value})
        )
    );
    return(expandedEntities)
);

addSetting(e, entry) ->
(
    if(global_settings:e != null,
        global_settings:e = global_settings:e + {entry -> null},
        global_settings = global_settings + {e -> {entry -> null}}
    );
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

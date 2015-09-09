function Point(px, py) {this.x = px; this.y = py;}
function Line(pa, pb, s, p) {this.a = pa; this.b = pb; this.point = p; this.slope = s;}

function Graph()
{
	this.canvas = arguments[0];
	this.ctx = this.canvas.getContext("2d");
	
	var that = this;
	
	this.canvas.addEventListener("mousedown", function(e){that.updateMouse(e);}, false);
	this.canvas.addEventListener("mousemove", function(e){that.updateMouse(e);}, false);
	this.canvas.addEventListener("mouseup", function(e){that.updateMouse(e);}, false);
	this.canvas.addEventListener("touchstart", function(e){that.updateTouch(e);}, false);
	this.canvas.addEventListener("touchmove", function(e){that.updateTouch(e);}, false);
	this.canvas.addEventListener("touchend", function(e){that.updateTouch(e);}, false);
	this.canvas.addEventListener("touchcancel", function(e){that.updateTouch(e);}, false);
	
	this.settings =
	{
		offset: new Point(0, 30),
		domain: new Point(undefined, undefined),
		range: new Point(undefined, undefined),
		get plotSize()
		{ return new Point(this.pixelPerUnit.x * (this.domain.y - this.domain.x), this.pixelPerUnit.y * (this.range.y - this.range.x)); },
		pixelPerUnit: new Point(1, 1),
		unitPerTick: new Point(undefined, undefined),
		get gridSize()
		{ return new Point(this.unitPerTick.x * this.pixelPerUnit.x, this.unitPerTick.y * this.pixelPerUnit.y); },
		get size()
		{ return new Point(1, 1);},
		labelFrequency: new Point(undefined, undefined),
		xAxis: "",
		yAxis: "",
		zeroBoundAxis: true,
		drawGrid: true,
		drawCoords: true
	};
	
	var incomingSettings = arguments[1];
	for (var key in incomingSettings)
		if(this.settings.hasOwnProperty(key))
			this.settings[key] = incomingSettings[key];
		
	this.mouse =
	{
		down: new Point(),
		move: new Point(),
		up: new Point(),
		isDown: false,
		isUp: true
	};
	
	var decimal = parseFloat("0."+this.settings.unitPerTick.y.toString().split('.')[1]);
	this.ctx.font = "18px Helvetica";
	this.canvas.width = this.settings.plotSize.x + this.ctx.measureText(Math.max(Math.floor(Math.abs(this.settings.range.x)) + decimal, Math.floor(Math.abs(this.settings.range.y)) + decimal)).width * (this.settings.labelFrequency.y == 0 ? 0 : 1) + 83;
	this.canvas.height = this.settings.plotSize.y + 83;
	this.ctx.font = "16px Helvetica";
	
	this.settings.offset.x += this.ctx.measureText(Math.max(Math.floor(Math.abs(this.settings.range.x)) + decimal, Math.floor(Math.abs(this.settings.range.y)) + decimal)).width * (this.settings.labelFrequency.y == 0 ? 0 : 1) + this.ctx.measureText("-MOOO").width;
	
	this.draw();
	
	return this;
}

Graph.prototype = {
	draw: function()
	{
		var s = this.settings;
		//clear and restore the canvas
		this.ctx.restore();
		this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
		
		//border
		this.ctx.strokeStyle = "#0000FF";
		this.ctx.lineWidth = 2;
		this.ctx.strokeRect(0,0,this.canvas.width, this.canvas.height);
		
		this.ctx.translate(s.offset.x, s.offset.y);
		
		//plot
		this.ctx.fillStyle = "#F8F8F8";
		this.ctx.fillRect(0, 0, s.plotSize.x, s.plotSize.y);
		this.ctx.fillStyle = "#000000";
		this.ctx.strokeStyle = "#E0E0E0";
		this.ctx.font = "16px Helvetica";
		this.ctx.textAlign = "center";
		this.ctx.textBaseline = "top";
		this.ctx.beginPath();
		for( var i = 0; i <= s.plotSize.x / s.gridSize.x; i++)
		{
			var x = i * s.gridSize.x;
			
			if (s.drawGrid)
			{
				this.ctx.moveTo(x, s.plotSize.y);
				this.ctx.lineTo(x, 0);
			}
			
			if (!(i % s.labelFrequency.x) && s.labelFrequency.x > 0)
				this.ctx.fillText(s.domain.x + i * s.unitPerTick.x, x, s.plotSize.y + 5);
		}
		this.ctx.textAlign = "right";
		this.ctx.textBaseline = "middle";
		for( var i = 0; i <= s.plotSize.y / s.gridSize.y; i++)
		{
			var y = s.plotSize.y - i * s.gridSize.y;
			
			if (s.drawGrid)
			{
				this.ctx.moveTo(0, y);
				this.ctx.lineTo(s.plotSize.x, y);
			}
			
			if (!(i % s.labelFrequency.y) && s.labelFrequency.y > 0)
				this.ctx.fillText(s.range.x + i * s.unitPerTick.y, -5, y);
		}
		this.ctx.stroke();
		
		//axis
		this.ctx.strokeStyle = "#000000";
		this.ctx.lineWidth = 1;
		this.ctx.beginPath();
		var axisOffset = new Point(
		(s.zeroBoundAxis ? Math.max(0, Math.min(s.plotSize.x, -s.domain.x * s.pixelPerUnit.x)) : 0),
		(s.zeroBoundAxis ? Math.min(s.plotSize.y, Math.max(0, s.range.y * s.pixelPerUnit.y)) : s.plotSize.y));
		this.ctx.moveTo(axisOffset.x, 0);
		this.ctx.lineTo(axisOffset.x, s.plotSize.y);
		this.ctx.moveTo(0, axisOffset.y);
		this.ctx.lineTo(s.plotSize.x, axisOffset.y);
		this.ctx.stroke();
		
		//label offsets
		var decimal = parseFloat("0."+s.unitPerTick.y.toString().split('.')[1]);
		var labelOffset = new Point(this.ctx.measureText("Mo").width, this.ctx.measureText(Math.max(Math.floor(Math.abs(s.range.x)) + decimal, Math.floor(Math.abs(s.range.y)) + decimal)).width * (s.labelFrequency.y == 0 ? 0 : 1) + this.ctx.measureText("-M").width);
		
		//x axis label
		this.ctx.textAlign = "center";
		this.ctx.textBaseline = "top";
		this.ctx.font = "24px Helvetica";
		this.ctx.fillText(s.xAxis, s.plotSize.x / 2, s.plotSize.y + labelOffset.x);
		
		//y axis label
		this.ctx.translate(-labelOffset.y,  s.plotSize.y / 2);
		this.ctx.rotate(3 * Math.PI / 2);
		this.ctx.textBaseline = "bottom";
		this.ctx.fillText(s.yAxis, 0, 0);
		this.ctx.rotate(-3 * Math.PI / 2);
		this.ctx.translate(labelOffset.y,  -(s.plotSize.y / 2));
		
		//coordinates
		if (s.drawCoords)
		{
			var coordOffsetY = this.ctx.measureText(Math.max(Math.abs(this.settings.domain.x), Math.abs(this.settings.domain.y))).width;
			var coordOffsetX = coordOffsetY * 2 + this.ctx.measureText("y: ").width;
			this.ctx.textAlign = "left";
			this.ctx.textBaseline = "top";
			this.ctx.fillText("x: " + (isNaN(Math.round(this.mouse.move.x)) ? 0 : Math.round(this.mouse.move.x)), s.plotSize.x - coordOffsetX - 20, s.plotSize.y + labelOffset.x);
			this.ctx.fillText("y: " + (isNaN(Math.round(this.mouse.move.y)) ? 0 : Math.round(this.mouse.move.y)), s.plotSize.x - coordOffsetY - 10, s.plotSize.y + labelOffset.x);
		}
		
		this.ctx.translate(-s.offset.x, -s.offset.y);
		
		//clipping mask for plotting
		this.ctx.save();
		this.ctx.rect(s.offset.x, s.offset.y, s.plotSize.x, s.plotSize.y);
		this.ctx.clip();
	},
	updateMouse: function(e)
	{
		var p = this.pageToPlot(new Point(e.pageX, e.pageY));
		var type = e.type.replace("mouse", '');
		
		if ((type == "down" || type == "move") && (!(e.buttons & 1) && e.button != 0))
			return;
		
		if (this.pointInBounds(p))
		{
			this.mouse[type].x = p.x;
			this.mouse[type].y = p.y;
			if (type == "down")
			{
				this.mouse.isDown = true;
				this.mouse.isUp = false;
			}
			else if (type == "up")
			{
				this.mouse.isDown = false;
				this.mouse.isUp = true;
			}
		}
	},
	updateTouch: function(e)
	{
		var type = e.type.replace("touch",'');
		var touches = e.changedTouches;
		
		for(var i = 0; i < touches.length; i++)
		{
			var touchID, event;
			
			switch(type)
			{
				case "start":
					event = new MouseEvent("mousedown",
					{screenX: touches[i].screenX, screenY: touches[i].screenY,
					 clientX: touches[i].clientX, clientY: touches[i].clientY});
				break;
				case "move":
					event = new MouseEvent("mousemove",
					{screenX: touches[i].screenX, screenY: touches[i].screenY,
					 clientX: touches[i].clientX, clientY: touches[i].clientY});
				break;
				case "cancel":
				case "end":
					event = new MouseEvent("mouseup",
					{screenX: touches[i].screenX, screenY: touches[i].screenY,
					 clientX: touches[i].clientX, clientY: touches[i].clientY});
				break;
				default:
				break;
			}
			this.canvas.dispatchEvent(event);
		}
		
		if (e.touches.length > 0)
		{
			this.mouse.isDown = true;
			this.mouse.isUp = false;
		}
		
		if (e.touches.length == 1 && type == "move")
			e.preventDefault();
	},
	plotPoint: function(p, r, fill)
	{
		r = typeof r !== "undefined" ? r : 2;
		fill = typeof fill !== "undefined" ? fill : true;
		
		p = this.plotToCanvas(p);
		this.ctx.beginPath();
		this.ctx.arc(p.x, p.y, r, 0, 2 * Math.PI);
		if (fill)
			this.ctx.fill();
		else
			this.ctx.stroke();
	},
	plotLine: function(p1, p2)
	{
		p1 = this.plotToCanvas(p1);
		p2 = this.plotToCanvas(p2);
		this.ctx.beginPath();
		this.ctx.moveTo(p1.x, p1.y);
		this.ctx.lineTo(p2.x, p2.y);
		this.ctx.stroke();
	},
	plotSlope: function(p, slope)
	{
		var p1 = new Point(this.settings.domain.x, p.y - slope * (p.x - this.settings.domain.x));
		if (!this.pointInBounds(p1))
		{
			p1.y = p1.y < this.settings.range.x ? this.settings.range.x : this.settings.range.y;
			p1.x = p.x - (p.y - p1.y)/slope;
		}
		var p2 = new Point(this.settings.domain.y, p.y - slope * (p.x - this.settings.domain.y));
		if (!this.pointInBounds(p2))
		{
			p2.y = p2.y < this.settings.range.x ? this.settings.range.x : this.settings.range.y;
			p2.x = p.x - (p.y - p2.y)/slope;
		}
		this.plotLine(p1, p2);
		
		return new Line(p1, p2, slope, new Point(p.x, p.y));
	},
	plotPoly: function(points, closed)
	{
		closed = typeof closed !== "undefined" ? closed : false;
		
		if (typeof points == "undefined")
			return;
		
		var length = Object.keys(points).length;
		for (var i = 0; i < length - 1; i++)
			this.plotLine(points[i], points[i+1]);
		if (closed)
			this.plotLine(points[length - 1], points[0]);
	},
	plotFunction: function(func, x, step, start, end)
	{
		x = typeof x !== "undefined" ? x : true;
		step = typeof step !== "undefined" ? step : 1;
		start = typeof start !== "undefined" ? start : this.settings.domain.x;
		end = typeof end !== "undefined" ? end : this.settings.domain.y;
		
		var i = start, funcValue;
		var points = [];
		while (i < end)
		{
			funcValue = func(i);
			if (typeof funcValue !== "undefined")
				points.push(new Point(x?i:funcValue, x?funcValue:i));
			else
			{
				this.plotPoly(points);
				points = [];
			}
			i+= step;
			if (i > end)
				i = end;
		}
		if (typeof funcValue !== "undefined")
			points.push(new Point(x?i:funcValue, x?funcValue:i));
		this.plotPoly(points);
	},
	pageToPlot: function(p)
	{
		var x = (p.x - this.canvas.offsetLeft - this.settings.offset.x) / this.settings.pixelPerUnit.x + this.settings.domain.x;
		var y = (this.canvas.height - (p.y - this.canvas.offsetTop) - (this.canvas.height - (this.settings.offset.y + this.settings.plotSize.y))) / this.settings.pixelPerUnit.y + this.settings.range.x;
		return new Point(x, y);
	},
	plotToCanvas: function(p)
	{
		var x = (p.x - this.settings.domain.x) * this.settings.pixelPerUnit.x + this.settings.offset.x;
		var y = this.settings.plotSize.y - ((p.y - this.settings.range.x) * this.settings.pixelPerUnit.y) + this.settings.offset.y;
		return new Point(x, y);
	},
	pointInBounds: function(p)
	{
		return (p.x >= this.settings.domain.x && p.x <= this.settings.domain.y && p.y >= this.settings.range.x && p.y <= this.settings.range.y)
	}
}
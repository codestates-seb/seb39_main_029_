import Button from "./Button";

const COLOR_BUTTON = {
  BLUE: {
    bgcolor: "#0995ff",
    color: "#FFFFFF",
    bordercolor: "#0995ff",
    hovercolor: "#0074cc",
  },
  GREY: {
    bgcolor: "#E1ECF4",
    color: "#39739D",
    bordercolor: "#7aa7c7",
    hovercolor: "#b3d3ea",
  },
};

function ColorButton({ mode, ...props }) {
  const { bgcolor, color, bordercolor, hovercolor } = COLOR_BUTTON[mode];
  return (
    <Button
      padding={props.padding ?? 8}
      ftsize={props.ftsize ?? 13}
      onClick={props.onClick}
      text={props.text ?? "button"}
      bgcolor={bgcolor}
      color={color}
      bordercolor={bordercolor}
      hovercolor={hovercolor}
    />
  );
}

export default ColorButton;

import React, { useCallback } from "react";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";

const Markdown = React.memo(({ linkStopPropagation, ...props }) => {
  const handleLinkCLick = useCallback((event) => {
    event.linkStopPropagation();
  }, []);

  const linkRenderer = useCallback(
    ({ node, ...linkProps }) => <a {...linkProps} onClick={handleLinkCLick} />,
    [handleLinkCLick]
  );

  let renderers;

  if (linkStopPropagation) {
    renderers = {
      link: linkRenderer,
    };
  }

  return (
    <ReactMarkdown
      {...props}
      remarkPlugins={[remarkGfm]}
      components={renderers}
    />
  );
});

export default Markdown;

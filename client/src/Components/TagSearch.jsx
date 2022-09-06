import styled from "styled-components";
import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import PostBox from "./PostBox";

function TagSearch() {
  const token = localStorage.getItem("accessToken");
  const location = useLocation();
  const url = location.pathname.substring(11);

  const [Tags, setTags] = useState({});

  useEffect(() => {
    axios
      .get(
        `${process.env.REACT_APP_STACKOVERFLOW}/v1/posts?page=1&size=5&arrange=createdAt&tagCheckId=${url}`,
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then((res) => {
        setTags(res.data.data);
      });
  }, []);

  return (
    <Container>
      <Explain>
        <div className="h">Tags</div>
        <div className="p">
          A tag is a keyword or label that categorizes your question with other,
          similar questions. Using the right tags makes it easier for others to
          find and answer your question.
        </div>
        {Object.values(Tags).map((el) => {
          return (
            <div className="l" key={el.postId}>
              <PostBox post={el} />
            </div>
          );
        })}
      </Explain>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  height: 100vmax;
`;
const Explain = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 30px;
  .h {
    font-weight: 500;
    font-size: var(--header-font);
    margin: 0 30px 20px 0px;
  }
  .p {
    margin: 0 0 30px 0;
    font-size: var(--text-font);
  }
  .l {
    width: 100%;
  }
`;

export default TagSearch;

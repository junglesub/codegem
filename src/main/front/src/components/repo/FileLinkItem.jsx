import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { TextField, Typography, Box, Button, Link } from "@mui/material";
import MarkdownPreview from "@uiw/react-markdown-preview";
import { useFetchBe, useFetchGh } from "../../tools/api";
import CommentBoxUi from "../CommentBoxUi";

const FileLinkItem = ({ repo, filename, addItem, addedData, ghUser }) => {
  const fetchBe = useFetchBe();
  const fetchGh = useFetchGh();

  const [saving, setSaving] = useState(false);

  const [questionId, setQuestionId] = useState(
    filename.match(
      /(?<![a-zA-Z0-9])([a-zA-Z][0-9]{1,3})(?![a-zA-Z0-9])/g
    )?.[0] ?? ""
  );
  const [questionData, setQuestionData] = useState(null);
  const [fileContent, setFileContent] = useState("");
  const [additionalContent, setAdditionalContent] = useState("");

  useEffect(() => {
    fetchGh(`https://api.github.com/repos/${repo}/contents/${filename}`).then(
      (data) => setFileContent(decodeURIComponent(atob(data.content)))
    );
  }, []);

  useEffect(() => {
    if (!addedData) return;
    setQuestionId(addedData.questionId);
    setAdditionalContent(addedData.additionalContent);
  }, [addedData]);

  console.log(repo);

  // console.log("week1/_홍길동_A106_20250109.py".match(/([a-zA-Z][0-9]{1,3})/g));
  // console.log(
  //   "week1/_홍길동_A106_20250109.py".match(
  //     /[^a-zA-Z0-9]([a-zA-Z][0-9]{1,3})[^a-zA-Z0-9]/g
  //   )
  // );
  console.log(
    "week1/A1_홍길동_A106_20250109.py".match(
      /(?<![a-zA-Z0-9])([a-zA-Z][0-9]{1,3})(?![a-zA-Z0-9])/g
    )
  );

  console.log(filename.split(".")?.at(filename.split(".").length - 1));

  useEffect(() => {
    validateQuestionId(questionId);
  }, [questionId]);

  const validateQuestionId = async (id) => {
    // Example validation: Question ID must be a number and > 0
    try {
      const questionDataDoc = await fetchBe(`/pps/findcode/${id}`);
      if (!questionDataDoc.link) throw "Invalid Question";
      setQuestionData(questionDataDoc);
    } catch {
      setQuestionData(null);
      return "Can't find question";
    }
  };

  const handleChange = (event) => {
    const input = event.target.value;
    setQuestionId(input);
  };

  const fileExt = filename.split(".")?.at(filename.split(".").length - 1);

  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
      <Typography variant="h6" gutterBottom>
        File: {filename}
      </Typography>
      <TextField
        label="Enter Question ID"
        variant="outlined"
        value={questionId}
        onChange={!addedData ? handleChange : null}
        disabled={addedData}
        fullWidth
      />
      {questionData ? (
        <Typography variant="body1">
          <Link
            href={questionData.link}
            target="_blank"
            rel="noopener noreferrer"
          >
            [{questionData.source}] {questionData.title}
          </Link>
        </Typography>
      ) : (
        <Typography variant="body1" color="red">
          Can't find question
        </Typography>
      )}

      <Box sx={{ width: "100%" }}>
        <MarkdownPreview
          source={`
\`\`\`${fileExt} showLineNumbers
${fileContent}
\`\`\`
          `}
        />
      </Box>
      <CommentBoxUi
        user={ghUser}
        disabled={addedData}
        additionalContent={additionalContent}
        setAdditionalContent={setAdditionalContent}
      />
      {addedData?.githubIssueId && (
        <Link
          href={`https://github.com/${repo}/issues/${
            addedData?.githubIssueId ?? ""
          }`}
          target="_blank"
          rel="noopener noreferrer"
        >
          {`https://github.com/${repo}/issues/${
            addedData?.githubIssueId ?? ""
          }`}
        </Link>
      )}
      {!addedData && (
        <Button
          onClick={async () => {
            setSaving(true);
            fetchBe("/solution/create", "POST", {
              githubRepoId: repo,
              githubFilePath: filename,
              title: questionData?.title || questionId,
              platform: questionData?.source || "unknown",
              problemUrl: questionData?.link ?? "",
              code: fileContent,
              message: additionalContent,
              ext: fileExt,
            })
              .then((data) =>
                addItem({
                  questionId,
                  additionalContent,
                  questionData,
                  githubIssueId: data?.githubIssueId || "",
                })
              )
              .finally(() => {
                setSaving(false);
              });
          }}
          disabled={saving}
        >
          저장
        </Button>
      )}
    </Box>
  );
};

export default FileLinkItem;
